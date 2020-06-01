package pt.isel.mpd.weather.dto;

import java.time.LocalDate;

public class WeatherInfo {

    public final LocalDate date;
    public final int tempC;
    public final double precipMM;
    public final String desc;

    public WeatherInfo(LocalDate date, int tempC, double precipMM, String desc) {
        this.date = date;
        this.tempC = tempC;
        this.precipMM = precipMM;
        this.desc = desc;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTempC() {
        return tempC;
    }

    public double getPrecipMM() {
        return precipMM;
    }

    public String getDesc() {
        return desc;
    }

    public static WeatherInfo valueOf(String line) {
        String[] words = line.split(",");
        LocalDate date = LocalDate.parse(words[0]);
        int temp = Integer.parseInt(words[2]);
        double precip = Double.parseDouble(words[11]);
        return new WeatherInfo(date, temp, precip, words[10]);
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
            "date=" + date +
            ", tempC=" + tempC +
            ", precipMM=" + precipMM +
            ", desc='" + desc + '\'' +
            '}';
    }
}
