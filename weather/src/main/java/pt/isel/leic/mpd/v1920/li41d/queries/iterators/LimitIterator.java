package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import java.util.Iterator;

public class LimitIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIterator;
    private final int n;

    public LimitIterator(Iterator<T> srcIterator, int n) {
        this.srcIterator = srcIterator;
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
