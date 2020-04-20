package pt.isel.leic.mpd.v1920.li41d.weather.api.dto;

public class PastWeatherDataDto {

    private final PastWeatherDataWeatherDto[] weather;

    public PastWeatherDataDto(PastWeatherDataWeatherDto[] weather) {
        this.weather = weather;
    }

    public PastWeatherDataWeatherDto[] getWeather() {
        return weather;
    }
}
