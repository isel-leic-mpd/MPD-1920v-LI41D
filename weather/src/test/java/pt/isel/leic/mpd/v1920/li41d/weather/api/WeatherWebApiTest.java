package pt.isel.leic.mpd.v1920.li41d.weather.api;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.utils.FileRequest;
import pt.isel.leic.mpd.v1920.li41d.weather.utils.Request;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public abstract class  WeatherWebApiTest {
    private final WorldWeatherOnlineApi weatherWebApi;


    public WeatherWebApiTest(Request request) {
        weatherWebApi = new WorldWeatherOnlineApi(request);
    }

    @Test
    public void shouldGetPastWeatherForALocation() throws IOException {
        String location = "Lisbon";
        final LocalDate from = LocalDate.parse("2020-01-01");
        final LocalDate to = LocalDate.parse("2020-01-31");

        final Iterable<DailyWeatherInfo> weatherInfos = weatherWebApi.pastWeather(from, to, location);


        assertEquals(31, count(weatherInfos));


    }

    private void assertIfInRange(double value, float limit) {
        Assert.assertTrue(value <= limit && value >= -limit);
    }

    private void assertNotNullNorEmpty(String str) {
        Assert.assertNotNull(str);
        Assert.assertFalse(str.isEmpty());
    }


    public static <T> int count(Iterable<T> src) {

        int i = 0;
        for (T t : src) { ++i; }

        return i;
    }
}
