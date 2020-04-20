package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import pt.isel.leic.mpd.v1920.li41d.utils.function.MyFunction;

import java.util.Iterator;
import java.util.function.Consumer;

public class  MapIterator<T, R> extends BaseIterator<R> {
    private final Iterator<T> srcIterator;
    private final MyFunction<T, R> mapper;

    public MapIterator(Iterator<T> srcIterator, MyFunction<T,R> mapper) {
        this.srcIterator = srcIterator;
        this.mapper = mapper;
    }

    @Override
    protected boolean tryAdvance(Consumer<R> consumer) {
        if(!srcIterator.hasNext()) return false;

        consumer.accept(mapper.apply(srcIterator.next()));
        return true;
    }
}
