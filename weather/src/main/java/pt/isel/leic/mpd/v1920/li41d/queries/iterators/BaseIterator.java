package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class BaseIterator<T> implements Iterator<T> {
    private Optional<T> next = Optional.empty();


    @Override
    public boolean hasNext() {
        if (next.isPresent()) return true;

        return tryAdvance(t -> next = Optional.of(t));

    }

    @Override
    public T next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        final T t = next.get();
        next = Optional.empty();
        return t;
    }

    protected abstract boolean tryAdvance(Consumer<T> consumer);
}
