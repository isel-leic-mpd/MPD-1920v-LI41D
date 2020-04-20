package pt.isel.leic.mpd.v1920.li41d.weather.api;


import pt.isel.leic.mpd.v1920.li41d.utils.Request;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldWeatherOnlineApi implements WeatherApi {
    // Weather API documentation: https://www.worldweatheronline.com/developer/api/docs/historical-weather-api.aspx
    private static final String HOST = "http://api.worldweatheronline.com/premium/v1/";
    private static final String PATH_PAST_WEATHER = "past-weather.ashx?q=%s&date=%s&enddate=%s&tp=24&format=%s&key=%s";
    private static final String PATH_SEARCH = "search.ashx?query=%s&format=%s&key=%s";
    private static final String WEATHER_KEY = "cd74a204137f42db98b112845202802";
    private static final String FORMAT = "csv";
    private Request request;

    public WorldWeatherOnlineApi(Request request) {
        this.request = request;
    }

    private static DailyWeatherInfo toDailyWeatherInfo(String line) {
        String[] words = line.split(",");
        LocalDate date = LocalDate.parse(words[0]);
        int max = Integer.parseInt(words[1]);
        int min = Integer.parseInt(words[3]);
        return new DailyWeatherInfo(date, max, min);
    }


    protected String getPastWeatherUri(LocalDate fromDate, LocalDate toDate, String location) {
        return String.format(HOST + PATH_PAST_WEATHER, location, fromDate, toDate, FORMAT, WEATHER_KEY);
    }

    public Iterable<DailyWeatherInfo> pastWeather(LocalDate fromDate, LocalDate toDate, String location) throws IOException {
        String uri = getPastWeatherUri(fromDate, toDate, location);
        Stream<String> lines = request.getLines(uri);
        final List<DailyWeatherInfo> dwi = parse(lines).collect(Collectors.toList());
        return dwi;
    }

    private Stream<DailyWeatherInfo> parse(Stream<String> lines) {
        final int[] count = {0};

        return lines                                                            // Stream<String>
                .filter(line ->  !line.trim().startsWith("#"))                  // Stream<String>
                .skip(1)                                                        // Stream<String>
                .filter(line -> count[0]++ %2 == 0)                             // Stream<String>
                .map(WorldWeatherOnlineApi::toDailyWeatherInfo)                 // Stream<DailyWeatherInfo>
                ;
    }
}
