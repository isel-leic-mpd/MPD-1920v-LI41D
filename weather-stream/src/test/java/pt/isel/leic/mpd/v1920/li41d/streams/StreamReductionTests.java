package pt.isel.leic.mpd.v1920.li41d.streams;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamReductionTests {
    final Stream<String> stream = Stream.of("Corona", "virus", "sucks");

    @Test
    public void shouldReduceAStreamInAnImmutableWayToAValueOfTheStreamElementsType() {
        final String reducedString = stream
                .parallel()
                .reduce("", this::concat, (str1, str2) -> str1 + str2
                );
        assertEquals("-Corona-virus-sucks", reducedString);
    }

    private String concat(String s1, String s2) {
        System.out.println(String.format("Concatenating %s with %s in thread %d", s1, s2, Thread.currentThread().getId()));
        return s1 + "-" + s2;
    }


    @Test
    public void shouldReduceAStreamInAnImmutableWayToAValueOfADifferentTypeOfStreamElements() {
        final int countChars =stream
                .parallel()
                .reduce(
                    0,
                    (acc, curr) -> acc + curr.length(),
                    this::combineIntegers);
        assertEquals(16, countChars);
    }

    private Integer combineIntegers(Integer acc1, Integer acc2) {
        System.out.println(String.format("Called combiner with %d and %d", acc1, acc2));
        return acc1 + acc2;
    }

    @Test
    public void shouldReduceAStreamToAList() {
        final List<String> list =
            stream.parallel().
                reduce(new ArrayList(),
                (acc, curr) -> { acc.add(curr); return acc;},
                (acc1, acc2) -> { acc1.addAll(acc2); return acc1; });
        assertEquals(Arrays.asList("Corona", "virus", "sucks"), list);
    }




}
