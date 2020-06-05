package pt.isel.mpd.util;

import java.util.Comparator;
import java.util.function.Function;

public class Cmp<T, R extends Comparable<R>> implements Comparator<T> {

    private final Function<T, R> getter;

    public Cmp(Function<T, R> getter) {
        this.getter = getter;
    }

    public static <T,R extends Comparable<R>> Cmp<T,R> by(Function<T,R> getter) {
        return new Cmp<>(getter);
    }

    @Override
    public int compare(T o1, T o2) {
        R val1 = getter.apply(o1);
        R val2 = getter.apply(o2);
        return val1.compareTo(val2);
    }

    public <U extends Comparable<U>> Cmp<T, U> andThen(Function<T, U> getter2) {
        Comparator<T> self = this;
        return new Cmp<>(getter2) {
            @Override
            public int compare(T o1, T o2) {
                int res = self.compare(o1, o2); // <=> Cmp.this.compare(...)
                if(res != 0) return res;
                return getter2.apply(o1).compareTo(getter2.apply(o2));
            }
        };
    }
}
