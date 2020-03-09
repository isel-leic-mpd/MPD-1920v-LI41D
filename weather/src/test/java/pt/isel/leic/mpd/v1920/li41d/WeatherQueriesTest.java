package pt.isel.leic.mpd.v1920.li41d;

import org.junit.Test;
import pt.isel.leic.mpd.v1819.li41d.queries.weather.WeatherQueries;
import pt.isel.leic.mpd.v1920.li41d.weather.WeatherSearch;

import java.time.LocalDate;

public class WeatherQueriesTest {
    @Test
    public void maxTemperatureBetweenDatesInALocationTest() {
        // Arrange
        final LocalDate fromDate = LocalDate.of(2020, 3, 1);
        final LocalDate toDate = LocalDate.of(2020, 3, 5);
        String location = "Lisboa";


        WeatherSearch wq = new WeatherSearch();



        // Act
        Iterable<Integer> maxTemperatures = wq.getMaxTemperatures(fromDate, toDate, location);


        // Assert
    }



}
