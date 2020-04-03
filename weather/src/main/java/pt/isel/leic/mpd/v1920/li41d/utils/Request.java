package pt.isel.leic.mpd.v1920.li41d.utils;

import java.io.IOException;
import java.io.Reader;

/**
 * Interface that allows its users to obtain a list of strings with each line
 * of its text content.
 */

public interface Request {
    /**
     * Gets the lines corresponding to the text content of the givem {@code url}
     * @param url the url to get its content lines
     * @return the List of the url content lines
     */
    Iterable<String> getLines(String url) throws IOException;

    Reader getReader(String path) throws IOException;

}
