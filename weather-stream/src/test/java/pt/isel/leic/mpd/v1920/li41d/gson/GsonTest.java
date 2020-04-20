package pt.isel.leic.mpd.v1920.li41d.gson;

import com.google.gson.Gson;
import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.utils.FileRequest;
import pt.isel.leic.mpd.v1920.li41d.weather.api.dto.PastWeatherDataDto;
import pt.isel.leic.mpd.v1920.li41d.weather.api.dto.PastWeatherDto;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.assertNotNull;

public class GsonTest {
    final String LISBON_URI = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=Lisbon&date=2020-01-01&enddate=2020-01-31&tp=24&format=json&key=cd74a204137f42db98b112845202802";
    Gson gson = new Gson();

    @Test
    public void shouldGetAPastWeatherDtoInstanceFromJsonString() throws IOException {
        // Arrange
        FileRequest fr = new FileRequest(".json");

        // Act
        final Reader reader = fr.getReader(LISBON_URI);
        final PastWeatherDto pastWeatherDto = gson.fromJson(reader, PastWeatherDto.class);

        // Assert
        assertNotNull(pastWeatherDto);
        final PastWeatherDataDto data = pastWeatherDto.getData();
        assertNotNull(data);
        assertNotNull(data.getWeather());




    }
}
