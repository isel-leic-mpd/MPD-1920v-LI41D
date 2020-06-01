package org.javasync.idioms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BlockingCountLines {
    public static long countLines(String path1, String path2) throws IOException {
        final long count1 = Files.lines(Paths.get(path1)).count();
        final long count2 = Files.lines(Paths.get(path2)).count();

        return count1 + count2;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
