package pt.isel.mpd.weather;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import pt.isel.mpd.util.AsyncHttpRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.time.LocalDate.of;

public class WebApp {

    static final AsyncWeatherService service = new AsyncWeatherService(new AsyncWeatherRestful(new AsyncHttpRequest()));
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx -> ctx
                .contentType("text/html")
                .result("<html><h1>Hello World</h1></html>"));
        app.get("/weather/:location", WebApp::pastWeatherHandler);
        app.get("/locations/:location", WebApp::locationsWeatherHandler);
        System.out.println("Main ended");
    }

    private static void locationsWeatherHandler(Context context) {
        handler(context, WebApp::locations);
    }

    private static void pastWeatherHandler(Context context) throws IOException {
        handler(context, WebApp::pastWeather);
    }

    private static void handler(Context context, Function<Context, Observable<String>> getObservableString) {
        CompletableFuture<Void> cf = new CompletableFuture<>();
        context.result(cf);

        try {
            PrintWriter writer = context.res.getWriter();
            getObservableString.apply(context)
                    .doOnSubscribe(disp -> context.contentType("text/html"))
                    .doOnNext(item -> {
                        writer.write(item);
                        Thread.sleep(2000);
                        writer.flush();
                    })
                    .doOnComplete(() -> cf.complete(null))
                    .doOnError(cf::completeExceptionally)
                    .subscribe();
        } catch (IOException e) {
            cf.completeExceptionally(e);
        }
    }


    private static void pastWeatherHandlerSync(Context context) throws IOException {
        PrintWriter writer = context.res.getWriter();
        pastWeather(context)
                .doOnSubscribe(disp -> context.contentType("text/html"))
                .doOnNext(item -> {
                    writer.write(item);
                    writer.flush();
                })
                .doOnComplete(() -> context.result(""))
                .blockingSubscribe();
    }

    private static Observable<String> pastWeather(Context context) {
        String locationParam = context.pathParam("location");
        String strTo = context.queryParam("to");
        String strFrom = context.queryParam("from");
        LocalDate to = strTo == null ? LocalDate.now() : LocalDate.parse(strTo, formatter);
        LocalDate from = strFrom == null ? to.minusDays(30) : LocalDate.parse(strFrom, formatter);
        return service
                .search(locationParam)// Observable<Location>
                //.firstElement()  // Maybe<Location> <=> Optional + Async <=> CompletableFuture
                //.toObservable()  // Observable<Location>
                .flatMap(location -> location.pastWeather(from, to)) // Observable<Weather>
                .map(w -> String.format("<br><strong>%s</strong>: %s", w.date, w.desc));
    }


    private static Observable<String> locations(Context context) {
        String locationParam = context.pathParam("location");
        return service
                .search(locationParam)// Observable<Location>
                .map(location -> String.format("<br><strong>%s</strong>: %s - %s", location.getAreaName(), location.getRegion(), location.getCountry()));
    }
}