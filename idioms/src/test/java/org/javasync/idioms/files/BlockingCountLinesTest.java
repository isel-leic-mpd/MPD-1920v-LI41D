package org.javasync.idioms.files;

import org.javasync.idioms.BlockingCountLines;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BlockingCountLinesTest {
    @Test
    public void testCountLines() throws IOException {

        long count = BlockingCountLines.countLines(
                Resources.METAMORPHOSIS,
                Resources.DISCOURSE_ON_THE_METHOD);
        assertEquals(4745, count);
    }
}