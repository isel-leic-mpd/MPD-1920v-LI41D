package pt.isel.leic.mpd.v1920.li41d.streams;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;



public class MyCountingCollector<T> implements Collector<T, Long[], Long> {
    @Override
    public Supplier<Long[]> supplier() {
//        return this::accSupplier;

        Long[] acc = new Long[] {0L};
        return () -> acc;
    }

    private Long[] accSupplier() {
        System.out.println("Supplier called");
        return new Long[] {0L};

    }

    @Override
    public BiConsumer<Long[], T> accumulator() {
        return (acc, dontCare) -> acc[0]++;
    }

    @Override
    public BinaryOperator<Long[]> combiner() {
        return (acc1, acc2) -> new Long[] { acc1[0]+ acc2[0] };
    }

    @Override
    public Function<Long[], Long> finisher() {
        return (acc) -> acc[0];
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
