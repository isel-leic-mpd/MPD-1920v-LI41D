package pt.isel.leic.mpd.v1920.li41d.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract public class BaseRequest implements Request {
    @Override
    final public Iterable<String> getLines(String url) throws IOException {
        List<String> lines = new ArrayList<>();

        try(InputStream is = openStream(url)) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }


    @Override
    public Reader getReader(String path) throws IOException {
        return new InputStreamReader(openStream(path));
    }

    protected abstract InputStream openStream(String url) throws IOException;
}
