package pt.isel.leic.mpd.v1920.li41d.weather;

import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.weather.api.MemoryWeatherApi;
import pt.isel.leic.mpd.v1920.li41d.utils.DateUtils;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class WeatherQueriesTest {
    @Test
    public void maxTemperatureBetweenDatesInALocationTest() throws IOException {
        // Arrange
        final LocalDate fromDate = LocalDate.of(2020, 3, 1);
        final LocalDate toDate = LocalDate.of(2020, 3, 5);
        String location = "Lisbon";


        WeatherQueries wq = new WeatherQueries(new MemoryWeatherApi());

        // Act
        Iterable<Integer> maxTemperatures = wq.getMaxTemperatures(fromDate, toDate, location);


        // Assert
        assertNotNull("returned object must not be null", maxTemperatures);
        int count = 0;


//        final Iterator<Integer> iterator = maxTemperatures.iterator();
//        while (iterator.hasNext()) {
//            ++count;
//            int temp = iterator.next();
//            assertTrue(temp > -70);
//            assertTrue(temp < 60);
//
//        }
//
        for (Integer maxTemperature : maxTemperatures) {
            ++count;
            int temp = maxTemperature;
            assertTrue(temp > -70);
            assertTrue(temp < 60);
        }

        assertEquals(DateUtils.numDays(fromDate, toDate), count);



    }



}
