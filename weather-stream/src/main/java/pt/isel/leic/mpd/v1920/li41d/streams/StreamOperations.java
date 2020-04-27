package pt.isel.leic.mpd.v1920.li41d.streams;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamOperations {
    static <T> Stream<T> collapse(Stream<T> src) {
        return StreamSupport.stream(new CollapseSpliterator<>(src.spliterator()), false);
    }


    static class CollapseSpliterator<T> implements Spliterator<T> {
        private Spliterator<T> src;
        boolean firstElement = true;
        private T last = null;

        public CollapseSpliterator(Spliterator<T> src) {
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

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return src.estimateSize();
        }

        @Override
        public int characteristics() {
            return src.characteristics() & ~Spliterator.CONCURRENT;
        }
    }
}
