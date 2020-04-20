package pt.isel.leic.mpd.v1920.li41d.misc.methodReferences;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceTests {
    @Test
    public void methodReferenceUsage() {
        Supplier<Double> supInteger = () -> Math.random();
        Supplier<Double> supIntegerStaticMr = Math::random;        // Method reference to static method
        Supplier<Double> supIntegerInstanceMr = this::getDouble;   // Method reference to instance method of a known object

        String str = "SLB";

        BiFunction<String, Integer, Character> biFunction1  = new BiFunction<String, Integer, Character>() {
            @Override
            public Character apply(String s, Integer i) {
                return s.charAt(i);
            }
        };
        BiFunction<String, Integer, Character> biFunction  = (s, i) -> s.charAt(i);
        BiFunction<String, Integer, Character> biFunctionToInstanceMethod  = String::charAt; // Method reference to an arbitrary object
        BiFunction<String, Integer, String> biFunctionToInstanceMethod1  = String::substring; // Method reference to an arbitrary object

        biFunctionToInstanceMethod.apply(str, 2);
        biFunctionToInstanceMethod1.apply(str, 1);
    }


    private double getDouble() {
        return Math.random();
    }
}

