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
public class QueriesLazy<T> {

    private Iterable<T> src;

    private QueriesLazy(Iterable<T> src) {
        this.src = src;
    }

    public static <T> QueriesLazy<T> from(Iterable<T> src) {
        return new QueriesLazy<>(src);
    }


    public QueriesLazy<T> filter(MyPredicate<T> pred)  {
        return new QueriesLazy<>(() -> new FilterIterator(src.iterator(), pred));
        //return QueriesLazy.from(() -> new FilterIterator(src.iterator(), pred));
    }


    public <R> QueriesLazy<R> map(MyFunction<T,R> mappper)  {
        return QueriesLazy.from(() -> new MapIterator(src.iterator(), mappper));
    }

    public QueriesLazy<T> skip(int n)  {
        return QueriesLazy.from(() -> new SkipIterator(src.iterator(), n));
    }

    public QueriesLazy<T> limit(int n)  {
        return QueriesLazy.from(() -> new LimitIterator(src.iterator(), n));
    }


    public int count() {
        int i = 0;
        for (T t : src) { ++i; }

        return i;
    }

    public T[] toArray(IntFunction<T[]> arrayFactory) {
        final T[] destArray = arrayFactory.apply(count());
        int i = 0;
        for (T t : src) {
            destArray[i++] = t;
        }

        return destArray;
    }

    public Iterable<T> toIterable() {
        return src;
    }
}
