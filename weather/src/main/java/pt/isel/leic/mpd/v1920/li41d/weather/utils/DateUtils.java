package pt.isel.leic.mpd.v1920.li41d.weather.utils;

import java.time.Duration;
import java.time.LocalDate;

public class DateUtils {
    public static int numDays(LocalDate from, LocalDate to) {
        return from.until(to).getDays()+1;
    }
}
