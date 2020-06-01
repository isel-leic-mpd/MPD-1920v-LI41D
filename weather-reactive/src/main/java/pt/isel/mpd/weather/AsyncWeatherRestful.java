package pt.isel.mpd.weather;

import com.google.gson.Gson;
import pt.isel.mpd.util.AsyncRequest;
import pt.isel.mpd.weather.dto.LocationInfo;
import pt.isel.mpd.weather.dto.PastWeatherDataWeatherDto;
import pt.isel.mpd.weather.dto.PastWeatherDto;
import pt.isel.mpd.weather.dto.SearchApiResultDto;
import pt.isel.mpd.weather.dto.SearchDto;
import pt.isel.mpd.weather.dto.WeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AsyncWeatherRestful implements  AsyncWeatherApi  {
    final static String HOST = "http://api.worldweatheronline.com/premium/v1/";
    final static String PATH_PAST_WEATHER = "past-weather.ashx?q=%s,%s&date=%s&enddate=%s&tp=24&format=json&key=%s";
    final static String PATH_SEARCH = "search.ashx?query=%s&format=json&key=%s";
    final static String WEATHER_KEY;

    private final AsyncRequest req;
    private final Gson gson = new Gson();

    static {
        try(
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("WEATHER_KEY.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
        {
            WEATHER_KEY = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error reading WEATHER_KEY.txt. Put your world weather online key within WEATHER_KEY.txt in resources folder.");
        }
    }

    public AsyncWeatherRestful(AsyncRequest req) {
        this.req = req;
    }

    /**
     * E.g. http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=37.017,-7.933&date=2019-01-01&enddate=2019-01-30&tp=24&format=csv&key=10a7e54b547c4c7c870162131192102
     *
     * @param lat Location latitude
     * @param log Location longitude
     * @param from Beginning date
     * @param to End date
     * @return List of WeatherInfo objects with weather information.
     */
    public CompletableFuture<List<WeatherInfo>> pastWeather(double lat, double log, LocalDate from, LocalDate to) {
        String path = HOST + String.format(PATH_PAST_WEATHER, lat, log, from, to, WEATHER_KEY);
        return req
                .getReader(path)
                .thenApply(reader -> gson.fromJson(reader, PastWeatherDto.class))
                .thenApply(dto -> dto.getData().getWeather())
                .thenApply(Stream::of)
                .thenApply(strm -> strm.map(AsyncWeatherRestful::toPastWeather))
                .thenApply(strm -> strm.collect(toList()));
    }
    private static WeatherInfo toPastWeather(PastWeatherDataWeatherDto dto){
        return new WeatherInfo(
                LocalDate.parse(dto.getDate()),
                dto.getHourly()[0].getTempC(),
                dto.getHourly()[0].getPrecipMM(),
                dto.getHourly()[0].getWeatherDesc()
        );
    }
    /**
     * e.g. http://api.worldweatheronline.com/premium/v1/search.ashx?query=Oporto&format=tab&key=10a7e54b547c4c7c870162131192102
     *
     * @param query Name of the city you are looking for.
     * @return List of LocationInfo objects with location information.
     */
    public CompletableFuture<List<LocationInfo>> search(String query) {
        String path = HOST + String.format(PATH_SEARCH, query, WEATHER_KEY);
        return req
            .getReader(path)
            .thenApply(body -> gson.fromJson(body, SearchDto.class))
            .thenApply(dto -> dto.getSearch_api().getResult())
            .thenApply(Stream::of)
            .thenApply(strm -> strm.map(AsyncWeatherRestful::toLocationInfo))
            .thenApply(strm -> strm.collect(toList()));
    }

    private static LocationInfo toLocationInfo(SearchApiResultDto dto) {
        return new LocationInfo(
            dto.getCountry()[0].getValue(),
            dto.getRegion()[0].getValue(),
            Double.parseDouble(dto.getLatitude()),
            Double.parseDouble(dto.getLongitude()));
    }
}
