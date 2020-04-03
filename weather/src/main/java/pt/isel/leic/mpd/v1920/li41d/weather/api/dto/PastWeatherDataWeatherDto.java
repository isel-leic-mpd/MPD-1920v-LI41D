package pt.isel.leic.mpd.v1920.li41d.weather.api.dto;

public class PastWeatherDataWeatherDto {
    private final String date;
    private final int maxtempC;
    private final int mintempC;

    private final PastWeatherDataWeatherHourlyDto[] hourly;

    public PastWeatherDataWeatherDto(String date, int maxtempC, int mintempC, PastWeatherDataWeatherHourlyDto[] hourly) {
        this.date = date;
        this.maxtempC = maxtempC;
        this.mintempC = mintempC;
        this.hourly = hourly;
    }

    public String getDate() {
        return date;
    }

    public int getMaxtempC() {
        return maxtempC;
    }

    public int getMintempC() {
        return mintempC;
    }

    public PastWeatherDataWeatherHourlyDto[] getHourly() {
        return hourly;
    }
}
