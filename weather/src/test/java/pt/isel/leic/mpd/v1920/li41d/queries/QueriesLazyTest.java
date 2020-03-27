package pt.isel.leic.mpd.v1920.li41d.queries;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QueriesLazyTest {
    final List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica");
    final List<String> stringsWithNulls = Arrays.asList("Sport", null, "Lisboa", null,  "e", null, "Benfica");



    @Test
    public void shouldFilterAStringSequence() {
        // Arrange

        // Act
        final Iterable<String> filteredStrings = QueriesLazy.filter(strings, (s) -> s.length() > 5);

        // Assert
        Assert.assertArrayEquals(new String[] {"Lisboa", "Benfica"}, QueriesLazy.toArray(filteredStrings, size -> new String[size]));

    }

    @Test
    public void shouldFilterAStringSequenceWithNullValues() {
        // Arrange

        // Act
        final Iterable<String> filteredStrings = QueriesLazy.filter(stringsWithNulls, (s) -> s== null || s.length() > 5);

        // Assert
        final String[] filteredStringsA = QueriesLazy.toArray(filteredStrings, size -> new String[size]);
        Assert.assertArrayEquals(new String[] {null, "Lisboa", null, null, "Benfica"}, filteredStringsA);

    }

    @Test
    public void shouldMapAStringSequence() {
        // Arrange

        // Act
        final Iterable<Integer> sizes = QueriesLazy.map(strings, (s) -> s.length());

        // Assert
        Assert.assertArrayEquals(new Integer[] {5, 6, 1, 7},QueriesLazy.toArray(sizes, size -> new Integer[size]));
    }
}