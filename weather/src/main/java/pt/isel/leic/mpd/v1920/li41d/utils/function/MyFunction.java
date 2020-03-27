package pt.isel.leic.mpd.v1920.li41d.utils.function;

@FunctionalInterface
public interface MyFunction<T, R> {
    R apply(T t);
}
