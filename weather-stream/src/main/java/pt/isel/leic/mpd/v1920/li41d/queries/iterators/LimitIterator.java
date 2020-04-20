package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class LimitIterator<T> extends BaseIterator<T>  {
    private final Iterator<T> srcIterator;
    private int n;


    public LimitIterator(Iterator<T> srcIterator, int n) {
        this.srcIterator = srcIterator;
        this.n = n;
    }

    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        if(n == 0 || !srcIterator.hasNext()) return false;
        --n;
        consumer.accept(srcIterator.next());
        return true;
    }


}
