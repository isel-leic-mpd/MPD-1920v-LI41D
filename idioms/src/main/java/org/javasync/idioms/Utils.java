package org.javasync.idioms;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static long nrOfLines(String path) {
        try {
            sleep(2000);
            Path p = Paths.get(path);
            return Files.lines(p).count();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void sleep(int sleepTime) {
//        try {
            long start = System.currentTimeMillis();
            while((System.currentTimeMillis() - start) < sleepTime) {
                Thread.yield();
            }
            //Thread.sleep(milis);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
