package pt.isel.mpd.util;

import java.io.Reader;
import java.util.concurrent.CompletableFuture;

public interface AsyncRequest extends AutoCloseable {

    CompletableFuture<Reader> getReader(String path);
}
