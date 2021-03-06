package pt.isel.leic.mpd.v1920.li41d.utils;

import java.io.IOException;
import java.io.InputStream;

public class FileRequest extends BaseRequest {

    private String fileExtension = "";

    public FileRequest() { }

    public FileRequest(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    protected InputStream openStream(String url) throws IOException {
        String[] parts = url.split("/");
        String path = parts[parts.length-1]
                .replace('?', '-')
                .replace('&', '-')
                .replace('=', '-')
                .replace(',', '-')
                .toLowerCase();

        int idx = path.indexOf("tp-");

        path = path.substring(0, idx) + fileExtension;

        return ClassLoader.getSystemResource(path).openStream();
    }
}
