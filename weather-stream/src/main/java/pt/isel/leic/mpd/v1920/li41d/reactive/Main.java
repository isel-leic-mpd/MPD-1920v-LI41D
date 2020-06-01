package pt.isel.leic.mpd.v1920.li41d.reactive;

import java.util.concurrent.Flow.*;

class TempApp {
    public static void main(String[] args) {
        getTemperatures("New York").subscribe(new TempSubscriber());
    }

    private static Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

}
