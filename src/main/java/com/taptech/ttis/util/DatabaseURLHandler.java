package com.taptech.ttis.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Created by Gregory Lawson on 9/29/15.
 * use this class to allow arbirtrary urls that follow the url convention to become a java.net.URL.
 * mysql://rfp_app:rfp_app@localhost:3306/proposal?autoReconnect=true becomes a url that can become a
 * java.net.URL. The methods on the java.net.URL class properly parse the url into its parts.
 *
 */
public class DatabaseURLHandler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return null;
    }
}
