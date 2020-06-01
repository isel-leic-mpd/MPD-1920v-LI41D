package pt.isel.mpd.weather.model;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class AsyncLocation {
    private final String country;
    private final String region;
    private final double latitude;
    private final double longitude;
    private final BiFunction<LocalDate, LocalDate, Observable<Weather>> pastGetter;

    public AsyncLocation(
        String country,
        String region,
        double latitude,
        double longitude,
        BiFunction<LocalDate, LocalDate, Observable<Weather>> pastGetter)
     {
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pastGetter = pastGetter;
    }

    public Observable<Weather> pastWeather(LocalDate from, LocalDate to) {
        return pastGetter.apply(from, to);
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
            "country='" + country + '\'' +
            ", region='" + region + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }
}
