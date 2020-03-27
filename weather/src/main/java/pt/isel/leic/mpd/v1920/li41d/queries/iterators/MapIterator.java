package pt.isel.leic.mpd.v1920.li41d.queries.iterators;

import pt.isel.leic.mpd.v1920.li41d.utils.function.MyFunction;

import java.util.Iterator;

public class  MapIterator<T, R> implements Iterator<R> {
    private final Iterator<T> srcIterator;
    private final MyFunction<T, R> mapper;

    public MapIterator(Iterator<T> srcIterator, MyFunction<T,R> mapper) {
        this.srcIterator = srcIterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return srcIterator.hasNext();
    }

    @Override
    public R next() {
//        final T nextT = srcIterator.next();
//        final R nextR = mapper.apply(nextT);
//        return nextR;
        return mapper.apply(srcIterator.next());
    }
}
