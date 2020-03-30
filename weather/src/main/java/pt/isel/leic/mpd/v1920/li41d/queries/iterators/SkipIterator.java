package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class SkipIterator<T> extends BaseIterator<T>  {
    private final Iterator<T> srcIterator;
    private int n;

    public SkipIterator(Iterator<T> srcIterator, int n) {
        this.srcIterator = srcIterator;
        this.n = n;
    }

    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        while (n > 0 && srcIterator.hasNext()) {
            --n;
            next();
        }

        if(srcIterator.hasNext()) {
            consumer.accept(srcIterator.next());
            return true;
        }
        return false;
    }
}
