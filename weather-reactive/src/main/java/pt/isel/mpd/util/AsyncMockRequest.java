package pt.isel.mpd.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.CompletableFuture;

public class AsyncMockRequest implements AsyncRequest{

    @Override
    public CompletableFuture<Reader> getReader(String path) {
        int end = path.length() > 132 ? 132 : path.length();
        path = path.replace('&', '_')
            .replace(',', '_')
            .replace('=', '_')
            .replace('?', '_')
            .substring(45, end);
        path += ".csv";
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(in);
        return CompletableFuture.completedFuture(reader);
    }

    @Override
    public void close() throws Exception {

    }
}
