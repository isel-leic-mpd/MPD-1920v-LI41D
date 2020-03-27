package pt.isel.leic.mpd.v1920.li41d.queries;

import pt.isel.leic.mpd.v1920.li41d.utils.function.MyFunction;
import pt.isel.leic.mpd.v1920.li41d.utils.function.MyPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Class with several utility queries for Iterable sequences with eager implementations
 */
public abstract class QueriesEager {
    public static <T> Iterable<T> filter(Iterable<T> src, MyPredicate<T> pred)  {
        List<T> result = new ArrayList<>();

        for (T t : src) {
            if(pred.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


    public static <T, R> Iterable<R> map(Iterable<T> src, MyFunction<T,R> mappper)  {
        List<R> result = new ArrayList<>();

        for (T t : src) {
            result.add(mappper.apply(t));
        }
        return result;
    }
}
