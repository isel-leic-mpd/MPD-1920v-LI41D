package pt.isel.leic.mpd.v1920.li41d.weather.api.dto;

public class PastWeatherDto {
    private final PastWeatherDataDto data;


    public PastWeatherDto(PastWeatherDataDto data) {
        this.data = data;
    }


    public PastWeatherDataDto getData() {
        return data;
    }


}
