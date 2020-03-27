package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import pt.isel.leic.mpd.v1920.li41d.utils.function.MyPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIterator;
    private final MyPredicate<T> pred;
    private T next;
    private boolean nextPresent = false;

    public FilterIterator(Iterator<T> srcIterator, MyPredicate<T> pred) {
        this.srcIterator = srcIterator;
        this.pred = pred;
    }

    @Override
    public boolean hasNext() {
        if(nextPresent) return true;
        while (srcIterator.hasNext()) {
            next = srcIterator.next();
            if(pred.test(next)) {
                return nextPresent = true;
            }
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
