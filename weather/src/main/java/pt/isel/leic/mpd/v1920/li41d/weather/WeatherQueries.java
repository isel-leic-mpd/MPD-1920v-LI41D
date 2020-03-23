package pt.isel.leic.mpd.v1920.li41d.weather;

import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;
import pt.isel.leic.mpd.v1920.li41d.weather.api.WeatherApi;
import pt.isel.leic.mpd.v1920.li41d.weather.utils.DateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



/**
 * Class with methods for several weather queries
 */
public class WeatherQueries {

    private WeatherApi weatherApi;


    public WeatherQueries(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Iterable<Integer> getMaxTemperatures(LocalDate fromDate, LocalDate toDate, String location) throws IOException {
        Iterable<DailyWeatherInfo> weatherInfos = weatherApi.pastWeather(fromDate, toDate, location);

        List<Integer> maxTemperatures = new ArrayList<>(DateUtils.numDays(fromDate, toDate));

        for (DailyWeatherInfo weatherInfo : weatherInfos) {
            maxTemperatures.add(weatherInfo.getTempMaxC());
        }
        return maxTemperatures;
    }


    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMaxTemperaturesAbove(LocalDate fromDate, LocalDate toDate, String location, int thresholdTemp) throws IOException {
        Iterable<DailyWeatherInfo> weatherInfos = weatherApi.pastWeather(fromDate, toDate, location);

        List<DailyWeatherInfo> maxTemperatures = new ArrayList<>(DateUtils.numDays(fromDate, toDate));

        for (DailyWeatherInfo weatherInfo : weatherInfos) {
            if(weatherInfo.getTempMaxC() >= thresholdTemp) {
                maxTemperatures.add(weatherInfo);
            }
        }
        return maxTemperatures;
    }

}
