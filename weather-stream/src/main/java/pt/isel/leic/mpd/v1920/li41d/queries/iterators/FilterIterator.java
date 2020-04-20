package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import pt.isel.leic.mpd.v1920.li41d.utils.function.MyPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class FilterIterator<T> extends BaseIterator<T> {
    private final Iterator<T> srcIterator;
    private final MyPredicate<T> pred;


    public FilterIterator(Iterator<T> srcIterator, MyPredicate<T> pred) {
        this.srcIterator = srcIterator;
        this.pred = pred;
    }


    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        while (srcIterator.hasNext()) {
            T t = srcIterator.next();
            if(pred.test(t)) {
                consumer.accept(t);
                return true;
            }
        }
        return false;
    }
}
