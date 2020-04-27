package pt.isel.leic.mpd.v1920.li41d.streams;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class StreamOperations {
    static <T> Stream<T> collapse(Stream<T> src) {
        return StreamSupport.stream(new CollapseSpliterator<>(src.spliterator()), false);
    }


    static class CollapseSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
        private Spliterator<T> src;
        boolean firstElement = true;
        private T last = null;

        public CollapseSpliterator(Spliterator<T> src)  {
            super(src.estimateSize(), src.characteristics());
            this.src = src;
        }


        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            T lastAux = last;
            while(src.tryAdvance(t -> {
                if(firstElement) {
                    last = t;
                    firstElement = false;
                    action.accept(t);
                    return;
                }
                if(!(Objects.equals(t, last))) {
                    last = t;
                    action.accept(t);
                }
            }) && lastAux == last);

            return !(lastAux == last);
        }
    }


    public static <T> Supplier<Stream<T>> nocache(Stream<T> src) {
        return () -> src;
    }

    public static <T> Supplier<Stream<T>> badcache(Stream<T> src) {
        List<T> cache = src.collect(toList());
        return () -> cache.stream();  // cache::stream
    }
}
