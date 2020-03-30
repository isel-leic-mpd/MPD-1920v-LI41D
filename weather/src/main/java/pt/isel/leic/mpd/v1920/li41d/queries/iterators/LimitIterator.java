package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LimitIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIterator;
    private int n;

    Optional<T> next = Optional.empty();


    public LimitIterator(Iterator<T> srcIterator, int n) {
        this.srcIterator = srcIterator;
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        if(next.isPresent()) return true;
        if(n == 0 || !srcIterator.hasNext()) return false;
        --n;
        next = Optional.ofNullable(srcIterator.next());
        return true;
    }

    @Override
    public T next() {
        if(hasNext()) {

            final T t = next.get();
            next = Optional.empty();
            return t;
        }
        throw new NoSuchElementException();
    }
}
