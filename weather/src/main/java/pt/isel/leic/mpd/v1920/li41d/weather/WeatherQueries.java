package pt.isel.leic.mpd.v1920.li41d.weather;

import pt.isel.leic.mpd.v1920.li41d.queries.QueriesEager;
import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;
import pt.isel.leic.mpd.v1920.li41d.weather.api.WeatherApi;
import pt.isel.leic.mpd.v1920.li41d.utils.function.MyPredicate;

import java.io.IOException;
import java.time.LocalDate;


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

        return QueriesEager.map(weatherInfos, dwi -> dwi.getTempMaxC());
    }


    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMaxTemperaturesAbove(LocalDate fromDate, LocalDate toDate, String location, int thresholdTemp) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location,
                dailyWeatherInfo -> dailyWeatherInfo.getTempMaxC() >= thresholdTemp

        );
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMaxTemperaturesBetween(LocalDate fromDate, LocalDate toDate, String location, int min, int max) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location, weatherInfo -> weatherInfo.getTempMaxC() >= min && weatherInfo.getTempMaxC() <= max);
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMinTemperaturesAbove(LocalDate fromDate, LocalDate toDate, String location, int thresholdTemp) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location, weatherInfo -> weatherInfo.getTempMinC() >= thresholdTemp);
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMinTemperaturesBetween(LocalDate fromDate, LocalDate toDate, String location, int min, int max) throws IOException {
        return queryDailyWeatherInfo(fromDate, toDate, location, weatherInfo -> weatherInfo.getTempMinC() >= min && weatherInfo.getTempMinC() <= max);
    }


    public Iterable<DailyWeatherInfo> queryDailyWeatherInfo(LocalDate fromDate, LocalDate toDate, String location, MyPredicate<DailyWeatherInfo> pred) throws IOException {
        Iterable<DailyWeatherInfo> weatherInfos = weatherApi.pastWeather(fromDate, toDate, location);

        return QueriesEager.filter(weatherInfos, pred);
    }
}
