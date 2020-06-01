package pt.isel.mpd;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.Test;

public class RxJavaTests {
    @Test
    public void shoulMapAndFilterOnAnObservableSource() {
        @NonNull final Observable<Integer> range = Observable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);
        ;
        range.subscribe(new Observer<Integer>() {
            private Disposable disposable;
            int count = 0;
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("OnNext" + integer);
                if(count++ == 3) {
                    disposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("OnComplete");
            }
        });
        @NonNull final Disposable disposable = range
                .forEach(System.out::println);
    }
}
