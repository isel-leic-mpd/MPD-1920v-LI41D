package pt.isel.leic.mpd.v1920.li41d.streams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamReductionWithCollectTests {
    final List<String> coll = Arrays.asList("Corona", "virus", "sucks","Corona", "virus", "sucks","Corona", "virus", "sucks","Corona", "virus", "sucks","Corona", "virus", "sucks","Corona", "virus", "sucks","Corona", "virus", "sucks","Corona", "virus", "sucks");

    @Test
    public void shouldCountTheNumberOfStreamElementsWithCount() {
        final long streamSize = coll.stream().count();
        assertEquals(coll.size(), streamSize);
    }


    @Test
    public void shouldCountTheNumberOfStreamElementsWithReduce() {
        final long streamSize = coll.stream().reduce(
                0L,
                (prev, __) -> ++prev,
                (c1, c2) -> c1+c2
        );
        assertEquals(coll.size(), streamSize);
    }

    @Test
    public void shouldCountTheNumberOfStreamElementsWithCollectUsingCollectorsCounting() {
        final long streamSize = coll.stream().collect(Collectors.counting());
        assertEquals(coll.size(), streamSize);
    }

    @Test
    public void shouldCountTheNumberOfStreamElementsWithCollectUsingCustomCollector() {
        Collector<String, Long[], Long> collector = new MyCountingCollector<>();
        final long streamSize = coll.stream()
                .parallel()
                .collect(collector);
        assertEquals(coll.size(), streamSize);
    }

    @Test
    public void shouldReduceAStreamToAListUsingCustomCollector() {
        Collector<String, List<String>, List<String>> collector = new MyToListCollector<>();
        final List<String> list = coll.stream()
                .parallel()
                .collect(collector);
        assertEquals(coll, list);
    }


}
