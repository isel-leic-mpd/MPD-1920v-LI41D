package pt.isel.leic.mpd.v1920.li41d.weather.api;

import pt.isel.leic.mpd.v1920.li41d.utils.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemoryWeatherApi implements WeatherApi {
    @Override
    public Iterable<DailyWeatherInfo> pastWeather(LocalDate fromDate, LocalDate toDate, String location) {
        final int capacity = DateUtils.numDays(fromDate, toDate);
        List<DailyWeatherInfo> dailyWeatherInfos = new ArrayList<>(capacity);
        int max = 10;
        int min = 5;

        for(int i = 0; i < capacity; ++i) {
            dailyWeatherInfos.add(new DailyWeatherInfo(fromDate, max++, min++));
        }
        return dailyWeatherInfos;
    }
}
