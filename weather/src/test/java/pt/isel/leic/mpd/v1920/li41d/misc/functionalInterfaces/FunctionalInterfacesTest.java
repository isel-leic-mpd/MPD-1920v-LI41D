package pt.isel.leic.mpd.v1920.li41d.misc.functionalInterfaces;

import org.junit.Test;
import pt.isel.leic.mpd.v1920.li41d.weather.api.DailyWeatherInfo;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfacesTest {

    @Test
    public void predicateDefaultMethods() {
        Predicate<DailyWeatherInfo> dwiPredicate = (dwi) -> dwi.getTempMaxC() > 12;
        Predicate<DailyWeatherInfo> dwiPredicate1 = (dwi) -> dwi.getTempMinC() > 5;
        Predicate<DailyWeatherInfo> dwiPredicate2 = (dwi) -> dwi.getDate().getYear() > 2019;
        Predicate<DailyWeatherInfo> dwiPredicateNegated = dwiPredicate.negate();  // (dwi) -> dwi.getTempMaxC() <= 12;

        Predicate<DailyWeatherInfo> dwiPredicateNegated1 = Predicate.not(dwiPredicate);




        final Predicate<DailyWeatherInfo> and = dwiPredicate.and(dwiPredicate1).or(dwiPredicate2);
    }

    public void functionDefaultMethods() {
        Function<DailyWeatherInfo, LocalDate> f1 = dwi -> dwi.getDate();
        Function<DailyWeatherInfo, LocalDate> f2 = DailyWeatherInfo::getDate;

        Function<LocalDate, String> f3 = LocalDate::toString;

        DailyWeatherInfo dwi = new DailyWeatherInfo(LocalDate.now(), 12, 10);

        final String str = f3.apply(f1.apply(dwi));

        final Function<DailyWeatherInfo, String> fCombinedWithAndThen = f1.andThen(f3);
        final String str1 = fCombinedWithAndThen.apply(dwi);

        final Function<DailyWeatherInfo, String> compose = f3.compose(f1);
    }
}


interface MyPredicate<T> {

    boolean test(T t);

    default MyPredicate<T> negate() {
        return (t) -> !this.test(t);
    }
}