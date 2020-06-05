package pt.isel.mpd.util;

import java.util.Comparator;
import java.util.function.Function;

public interface Comparer<T> extends Comparator<T> {

    static <T,R extends Comparable<R>> Comparer<T> by(Function<T,R> getter) {
        return (o1, o2) -> getter.apply(o1).compareTo(getter.apply(o2));
    }

    default <U extends Comparable<U>> Comparer<T> andThen(Function<T, U> getter2) {
        return (o1, o2) -> {
            int res = this.compare(o1, o2);
            return res != 0
                ? res
                : getter2.apply(o1).compareTo(getter2.apply(o2));
        };
    }
}
