package pt.isel.leic.mpd.v1920.li41d.weather.api;

import pt.isel.leic.mpd.v1819.li41d.utils.HttpRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorldWeatherOnlineApi implements WeatherApi {
    // Weather API documentation: https://www.worldweatheronline.com/developer/api/docs/historical-weather-api.aspx
    private static final String HOST = "http://api.worldweatheronline.com/premium/v1/";
    private static final String PATH_PAST_WEATHER = "past-weather.ashx?q=%s&date=%s&enddate=%s&tp=24&format=%s&key=%s";
    private static final String PATH_SEARCH = "search.ashx?query=%s&format=%s&key=%s";
    private static final String WEATHER_KEY = "cd74a204137f42db98b112845202802";
    private static final String FORMAT = "csv";


    public Iterable<DailyWeatherInfo> pastWeather(LocalDate fromDate, LocalDate toDate, String location) throws IOException {
        HttpRequest httpReq = new HttpRequest();

        final String url = String.format(HOST + PATH_PAST_WEATHER, location, fromDate, toDate, FORMAT, WEATHER_KEY);
        Iterable<String> lines = httpReq.getLines(url);

        return parse(lines);
    }

    private Iterable<DailyWeatherInfo> parse(Iterable<String> lines) {
        List<DailyWeatherInfo> dailyWeatherInfos = new ArrayList<>();

        final Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext() && iterator.next().trim().startsWith("#"));

        while (iterator.hasNext()) {
            String line = iterator.next().trim();
            // TODO
        }


        return dailyWeatherInfos;
    }
}
