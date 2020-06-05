package pt.isel.mpd.weather;

import pt.isel.mpd.weather.dto.LocationInfo;
import pt.isel.mpd.weather.dto.WeatherInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface AsyncWeatherApi {
    CompletableFuture<List<WeatherInfo>> pastWeather(double lat, double log, LocalDate from, LocalDate to);
    CompletableFuture<List<LocationInfo>> search(String query);
}
