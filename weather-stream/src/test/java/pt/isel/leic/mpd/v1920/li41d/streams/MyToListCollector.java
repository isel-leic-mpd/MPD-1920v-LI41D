package pt.isel.leic.mpd.v1920.li41d.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class MyToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        //return () -> new ArrayList<>();
        return ArrayList<T>::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //return (acc, t) -> acc.add(t);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (acc1, acc2) -> { acc1.addAll(acc2); return acc1; };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        //return (acc) -> acc;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}
