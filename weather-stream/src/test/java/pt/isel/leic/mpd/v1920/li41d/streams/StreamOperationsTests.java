package pt.isel.leic.mpd.v1920.li41d.streams;

import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamOperationsTests {

    final List<Integer> numbers = Arrays.asList(null, 1,2,2,2, null, null, null, 3,3,4,4,4,5,6,7,7,1,1);
    @Test
    public void shouldCollapseEqualAdjacentElements() {
        final List<Integer> numbersCollapsed = StreamOperations.collapse(numbers.stream())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(null, 1,2, null, 3,4,5,6,7, 1), numbersCollapsed);

    }


    @Test
    public void noCacheTest() {
        final Stream<Character> src = Stream.iterate('a', prev -> ++prev);

        final Supplier<Stream<Character>> cacheSup = StreamOperations.nocache(src);

        final Stream<Character> stream1 = cacheSup.get();
        final Stream<Character> stream2 = cacheSup.get();

        final Spliterator<Character> spliterator1 = stream1.spliterator();

        // Exception obtaining another the spliterator for the same stream
        final Spliterator<Character> spliterator2 = stream2.spliterator();

        spliterator1.tryAdvance(System.out::println);
        spliterator2.tryAdvance(System.out::println);

    }

    @Test
    public void badCacheTest() {
        final Stream<Character> src = Stream.iterate('a', prev -> ++prev).limit(5);

        // Out od memory exception because src is an infinite stream
        final Supplier<Stream<Character>> cacheSup = StreamOperations.badcache(src);

        final Stream<Character> stream1 = cacheSup.get();
        final Stream<Character> stream2 = cacheSup.get();

        final Spliterator<Character> spliterator1 = stream1.spliterator();
        final Spliterator<Character> spliterator2 = stream2.spliterator();

        spliterator1.tryAdvance(System.out::println);
        spliterator2.tryAdvance(System.out::println);

    }
}
