package pt.isel.leic.mpd.v1920.li41d.queries;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class QueriesLazyTest {
    final List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica");
    final List<String> stringsWithNulls = Arrays.asList("Sport", null, "Lisboa", null,  "e", null, "Benfica");



    @Test
    public void shouldFilterAStringSequence() {
        // Arrange

        // Act
        final Iterable<String> filteredStrings = QueriesLazy
                .from(strings)
                .filter((s) -> s.length() > 5)
                .toIterable();

        // Assert
        Assert.assertArrayEquals(new String[] {"Lisboa", "Benfica"}, QueriesLazy.from(filteredStrings).toArray(size -> new String[size]));

    }

    @Test
    public void shouldFilterAStringSequenceWithNullValues() {
        // Arrange

        // Act
        final Iterable<String> filteredStrings = QueriesLazy.from(stringsWithNulls).filter((s) -> s== null || s.length() > 5).toIterable();

        // Assert
        final String[] filteredStringsA = QueriesLazy.from(filteredStrings).toArray(size -> new String[size]);
        Assert.assertArrayEquals(new String[] {null, "Lisboa", null, null, "Benfica"}, filteredStringsA);

    }

    @Test
    public void shouldMapAStringSequence() {
        // Arrange

        // Act
        final Iterable<Integer> sizes = QueriesLazy.from(strings).map(s -> s.length()).toIterable();

        // Assert
        Assert.assertArrayEquals(new Integer[] {5, 6, 1, 7}, QueriesLazy.from(sizes).toArray(size -> new Integer[size]));
    }


    @Test
    public void shouldFilterAndMapAStringSequence() {
        // Arrange

        // Act
        final Iterable<Integer> sizes = QueriesLazy.from(stringsWithNulls)
                .filter((s) -> s== null || s.length() > 5)
                .map((s) -> s.length())
                .toIterable();



        // Assert
        Assert.assertArrayEquals(new Integer[] {5, 6, 7},QueriesLazy.from(sizes).toArray(size -> new Integer[size]));
    }
}