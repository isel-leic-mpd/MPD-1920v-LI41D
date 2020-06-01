package pt.isel.mpd;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;
import pt.isel.mpd.util.AsyncHttpRequest;
import pt.isel.mpd.util.AsyncMockRequest;
import pt.isel.mpd.util.AsyncRequest;
import pt.isel.mpd.weather.AsyncWeatherRestful;
import pt.isel.mpd.weather.AsyncWeatherService;
import pt.isel.mpd.weather.model.Weather;

import java.io.Reader;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.time.LocalDate.of;
import static org.junit.Assert.assertEquals;

public class WeatherServiceTest {
    @Test public void testPastWeatherOfFaroCity() {
        AsyncWeatherService service = new AsyncWeatherService(new AsyncWeatherRestful(new AsyncHttpRequest()));
        int maxTempJan = service
            .search("Faro") // Observable<Location>
            .firstElement() // Maybe<Location> <=> Optional + Async <=> CompletableFuture
            .toObservable() // Observable<Location>
            .flatMap(faro -> faro.pastWeather(of(2020, 1, 1), of(2020, 1, 30))) // Observable<Weather>
            .map(Weather::getTempC) // Observable<Integer>
            .reduce(Integer::max)   // Maybe<Integer>
            .blockingGet(); // <=> join
        assertEquals(17, maxTempJan);
    }
    @Test public void testPastWeatherForManyMonths() {
        MediatorRequest req = new MediatorRequest(new AsyncHttpRequest());
        AsyncWeatherService service = new AsyncWeatherService(new AsyncWeatherRestful(req));
        @NonNull Observable<Integer> temps = service
            .search("Faro") // Observable<Location>
            .firstElement() // Maybe<Location> <=> Optional + Async <=> CompletableFuture
            .toObservable() // Observable<Location>
            .flatMap(faro -> faro.pastWeather(of(2020, 1, 1), of(2020, 4, 30))) // Observable<Weather>
            .map(Weather::getTempC);// Observable<Integer>
        System.out.println("Chaining asynchronous pipeline");
        Maybe<Integer> first = temps.firstElement();
        first
            .doOnSuccess(item -> System.out.println("Temp on first day: " + item))
            .blockingGet();


    }
}

class MediatorRequest implements AsyncRequest {
    final AsyncRequest req;
    int count = 0;

    public MediatorRequest(AsyncRequest req) {
        this.req = req;
    }

    @Override
    public CompletableFuture<Reader> getReader(String path) {
        count++;
        System.out.println(path);
        return req.getReader(path);
    }

    @Override
    public void close() throws Exception {
        req.close();
    }
}