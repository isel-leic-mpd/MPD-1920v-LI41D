package pt.isel.leic.mpd.v1920.li41d.queries;

import pt.isel.leic.mpd.v1920.li41d.queries.iterators.FilterIterator;
import pt.isel.leic.mpd.v1920.li41d.queries.iterators.LimitIterator;
import pt.isel.leic.mpd.v1920.li41d.queries.iterators.MapIterator;
import pt.isel.leic.mpd.v1920.li41d.queries.iterators.SkipIterator;
import pt.isel.leic.mpd.v1920.li41d.utils.function.MyFunction;
import pt.isel.leic.mpd.v1920.li41d.utils.function.MyPredicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;


/**
 * Class with several utility queries for Iterable sequences with lazy implementations
 */
public abstract class QueriesLazy {
    public static <T> Iterable<T> filter(Iterable<T> src, MyPredicate<T> pred)  {
        return () -> new FilterIterator(src.iterator(), pred);
    }


    public static <T, R> Iterable<R> map(Iterable<T> src, MyFunction<T,R> mappper)  {
        return () -> new MapIterator(src.iterator(), mappper);
    }

    public static <T, R> Iterable<R> skip(Iterable<T> src, int n)  {
        return () -> new SkipIterator(src.iterator(), n);
    }

    public static <T, R> Iterable<R> limit(Iterable<T> src, int n)  {
        return () -> new LimitIterator(src.iterator(), n);
    }


    public static <T> int count(Iterable<T> src) {

        int i = 0;
        for (T t : src) { ++i; }

        return i;
    }

    public static <T> T[] toArray(Iterable<T> src, IntFunction<T[]> arrayFactory) {
        final T[] destArray = arrayFactory.apply(count(src));
        int i = 0;
        for (T t : src) {
            destArray[i++] = t;
        }

        return destArray;
    }
}
