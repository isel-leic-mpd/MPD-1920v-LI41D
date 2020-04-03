package pt.isel.leic.mpd.v1920.li41d.queries;

import pt.isel.leic.mpd.v1920.li41d.queries.iterators.MapIterator;
import pt.isel.leic.mpd.v1920.li41d.utils.function.MyFunction;
import pt.isel.leic.mpd.v1920.li41d.utils.function.MyPredicate;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Queries<T>  {
    boolean tryAdvance(Consumer<T> consumer);

    static <T> Queries<T> of(Iterable<T> iter) {
        final Iterator<T> iterator = iter.iterator();
        return consumer -> {
            if (iterator.hasNext()) {
                consumer.accept(iterator.next());
                return true;
            }
            return false;
        };
    }

    default <R> Queries<R> map(Function<T,R> mappper)  {
        return consumer ->
             this.tryAdvance(t -> consumer.accept(mappper.apply(t)));
    }

    default <R> Queries<T> filter(Predicate<T> pred) {
        return consumer -> {
            boolean []predPassed = {false};
            while (!predPassed[0] && this.tryAdvance(t -> {
                if (pred.test(t)) {
                    consumer.accept(t);
                    predPassed[0] = true;
                }
            })) ;
            return predPassed[0];
        };
    }
}
