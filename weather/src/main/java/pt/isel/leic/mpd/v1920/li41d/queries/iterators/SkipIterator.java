package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIterator;
    private int n;
    private T next;
    private boolean nextPresent = false;

    public SkipIterator(Iterator<T> srcIterator, int n) {
        this.srcIterator = srcIterator;
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        if(nextPresent) return true;

        while (n-- > 0 && srcIterator.hasNext()) {
            next();
        }

        if(srcIterator.hasNext()) {
            next = srcIterator.next();
            nextPresent = true;
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if(hasNext()) {
            nextPresent = false;
            return next;
        }
        throw new NoSuchElementException();
    }
}
