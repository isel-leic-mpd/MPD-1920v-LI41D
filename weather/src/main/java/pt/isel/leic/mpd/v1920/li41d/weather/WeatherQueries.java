package pt.isel.leic.mpd.v1920.li41d.weather;

import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;
import pt.isel.leic.mpd.v1920.li41d.weather.api.WeatherApi;
import pt.isel.leic.mpd.v1920.li41d.weather.utils.DateUtils;
import pt.isel.leic.mpd.v1920.li41d.weather.utils.MyPredicate;

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
        return query(fromDate, toDate, location, new MyPredicate<DailyWeatherInfo>() {
            @Override
            public boolean test(DailyWeatherInfo dailyWeatherInfo) {
                return dailyWeatherInfo.getTempMaxC() >= thresholdTemp;
            }
        });
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMaxTemperaturesBetween(LocalDate fromDate, LocalDate toDate, String location, int min, int max) throws IOException {
        return query(fromDate, toDate, location, new MyPredicate<DailyWeatherInfo>() {
            @Override
            public boolean test(DailyWeatherInfo weatherInfo) {
                return weatherInfo.getTempMaxC() >= min && weatherInfo.getTempMaxC() <= max;
            }
        });
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMinTemperaturesAbove(LocalDate fromDate, LocalDate toDate, String location, int thresholdTemp) throws IOException {
        return query(fromDate, toDate, location, new MyPredicate<DailyWeatherInfo>() {
            @Override
            public boolean test(DailyWeatherInfo weatherInfo) {
                return weatherInfo.getTempMinC() >= thresholdTemp;
            }
        });
    }

    public Iterable<DailyWeatherInfo> getDailyWeatherInfoWithMinTemperaturesBetween(LocalDate fromDate, LocalDate toDate, String location, int min, int max) throws IOException {
        return query(fromDate, toDate, location, new MyPredicate<DailyWeatherInfo>() {
            @Override
            public boolean test(DailyWeatherInfo weatherInfo) {
                return weatherInfo.getTempMinC() >= min && weatherInfo.getTempMinC() <= max;
            }
        });
    }



    private Iterable<DailyWeatherInfo> query(LocalDate fromDate, LocalDate toDate, String location, MyPredicate<DailyWeatherInfo> pred) throws IOException {
        Iterable<DailyWeatherInfo> weatherInfos = weatherApi.pastWeather(fromDate, toDate, location);

        List<DailyWeatherInfo> maxTemperatures = new ArrayList<>(DateUtils.numDays(fromDate, toDate));

        for (DailyWeatherInfo weatherInfo : weatherInfos) {
            if(pred.test(weatherInfo)) {
                maxTemperatures.add(weatherInfo);
            }
        }
        return maxTemperatures;
    }
}
