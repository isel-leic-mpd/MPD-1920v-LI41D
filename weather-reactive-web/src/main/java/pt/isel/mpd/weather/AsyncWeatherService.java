package pt.isel.mpd.weather;

import io.reactivex.rxjava3.core.Observable;
import pt.isel.mpd.weather.dto.LocationInfo;
import pt.isel.mpd.weather.dto.WeatherInfo;
import pt.isel.mpd.weather.model.AsyncLocation;
import pt.isel.mpd.weather.model.Weather;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AsyncWeatherService {
    private final AsyncWeatherApi api;

    public AsyncWeatherService(AsyncWeatherApi api) {
        this.api = api;
    }

    public Observable<AsyncLocation> search(String query) {
        return fromCF(api
            .search(query)) // Observable<List<LocationInfo>>
            .flatMap(Observable::fromIterable)
            .map(this::toLocation);
    }

    public Observable<Weather> pastWeather(double lat, double log, LocalDate from, LocalDate to) {
        LocalDate begin = from.withDayOfMonth(1);
        int count = (int) ChronoUnit.MONTHS.between(from, to);
        return Observable
            .intervalRange(0, count + 1, 0, 2000, TimeUnit.MILLISECONDS)               // Observable<Integer> => COLD
            .map(inc -> begin.plusMonths(inc)) // Observable<LocalDate>
            .map(start -> api.pastWeather(lat, log, start, min(to, lastDay(start)))) // Observable<CF<List<WeatherInfo>>>
            .flatMap(cf -> fromCF(cf))   // Observable<List<WeatherInfo>>
            .flatMap(Observable::fromIterable) // Observable<WeatherInfo>
            .map(this::toWeather);
    }

    private LocalDate min(LocalDate to, LocalDate lastDay) {
        return to.isBefore(lastDay) ? to : lastDay;
    }

    private static <T> Observable<T> fromCF(CompletableFuture<T> cf) {
        return Observable.create(subscriber -> cf
            .thenAccept(item -> {
                subscriber.onNext(item);
                subscriber.onComplete();
            })
            .exceptionally(err -> {
                subscriber.onError(err);
                return null;
            }));
    }

    private static LocalDate lastDay(LocalDate start) {
        return start.withDayOfMonth(start.lengthOfMonth());
    }

    private AsyncLocation toLocation(LocationInfo dto) {
        return new AsyncLocation(
            dto.areaName,
            dto.country,
            dto.region,
            dto.latitude,
            dto.longitude,
            (from, to) -> this.pastWeather(dto.latitude, dto.longitude, from, to));
    }

    private Weather toWeather(WeatherInfo dto) {
        return new Weather(dto.date, dto.tempC, dto.precipMM, dto.desc);
    }

}
