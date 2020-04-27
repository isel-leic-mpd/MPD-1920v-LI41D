package pt.isel.leic.mpd.v1920.li41d.streams;

import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class StreamsTest {
    final List<DailyWeatherInfo> weatherInfos = Arrays.asList(
            new DailyWeatherInfo(LocalDate.of(2020, 02, 1), 15, 10),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 2), 13, 8),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 3), 12, 7),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 4), 11, 6),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 5), 10, 10),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 6), 15, 9),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 7), 14, 8),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 8), 13, 7),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 9), 15, 10),
            new DailyWeatherInfo(LocalDate.of(2020, 02, 1), 14, 9)
    );

    @Test
    public void streamOperationsPipelining() {
        final Stream<Integer> integerStream = weatherInfos
                .stream()                   // Stream<DailyWeatherInfo>
                .filter(
                        dwi -> {
                            System.out.println("filtering  " + dwi);
                            return dwi.getTempMaxC() > 13;
                        }
                )                           // Stream<DailyWeatherInfo>
                .map(dwi -> {
                    System.out.println("mapping  " + dwi);
                    return dwi.getTempMaxC();
                })                          // Stream<Integer>
                .limit(3)                   // Stream<Integer>
                ;

        // With internal iteration
        //final List<Integer> integerCollection = integerStream.collect(toList());


        // With external iteration (it's not supposed to be used externally)
//        final Spliterator<Integer> spliterator = integerStream.spliterator();
//        while(spliterator.tryAdvance(System.out::println));

        // or
        integerStream.forEach(System.out::println);
    }
}
