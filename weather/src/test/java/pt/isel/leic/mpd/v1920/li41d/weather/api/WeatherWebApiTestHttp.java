package pt.isel.leic.mpd.v1920.li41d.weather.api;

import pt.isel.leic.mpd.v1920.li41d.utils.HttpRequest;

public class WeatherWebApiTestHttp extends WeatherWebApiTest {
    public WeatherWebApiTestHttp() {
        super(new HttpRequest());
    }
}
