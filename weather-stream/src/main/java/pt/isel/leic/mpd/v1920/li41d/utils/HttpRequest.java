package pt.isel.leic.mpd.v1920.li41d.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpRequest extends BaseRequest {
    @Override
    protected InputStream openStream(String url) throws IOException {
        return new URL(url).openStream();
    }
}
