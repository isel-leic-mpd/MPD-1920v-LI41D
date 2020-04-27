package pt.isel.leic.mpd.v1920.li41d.streams;

import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StreamOperationsTests {

    final List<Integer> numbers = Arrays.asList(null, 1,2,2,2, null, null, null, 3,3,4,4,4,5,6,7,7,1,1);
    @Test
    public void shouldCollapseEqualAdjacentElements() {
        final List<Integer> numbersCollapsed = StreamOperations.collapse(numbers.stream())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(null, 1,2, null, 3,4,5,6,7, 1), numbersCollapsed);

    }
}
