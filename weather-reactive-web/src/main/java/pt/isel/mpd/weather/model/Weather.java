package pt.isel.mpd.weather.model;

import java.time.LocalDate;

public class Weather {

    public final LocalDate date;
    public final int tempC;
    public final double precipMM;
    public final String desc;

    public Weather(LocalDate date, int tempC, double precipMM, String desc) {
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

    public static Weather valueOf(String line) {
        String[] words = line.split(",");
        LocalDate date = LocalDate.parse(words[0]);
        int temp = Integer.parseInt(words[2]);
        double precip = Double.parseDouble(words[11]);
        return new Weather(date, temp, precip, words[10]);
    }

    @Override
    public String toString() {
        return "Weather {" +
            "date=" + date +
            ", tempC=" + tempC +
            ", precipMM=" + precipMM +
            ", desc='" + desc + '\'' +
            '}';
    }
}
