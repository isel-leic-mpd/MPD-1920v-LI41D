package org.javasync.idioms;

import java.util.concurrent.Callable;

public class MeasureTime {

    public static <T> T measure(Callable<T> callable) throws Exception {
        long start = System.currentTimeMillis();
        T ret =  callable.call();
        long end = System.currentTimeMillis();
        System.out.println("Work took " + (end-start) + "ms");

        return ret;
    }
}
