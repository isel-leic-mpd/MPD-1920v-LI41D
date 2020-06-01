package pt.isel.mpd.weather.dto;

public class PastWeatherDto {
    public PastWeatherDto(PastWeatherDataDto data) {
        this.data = data;
    }

    private final PastWeatherDataDto data;

    public PastWeatherDataDto getData() {
        return data;
    }
}
