package com.taptech.ttis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gregory Lawson on 2/1/16.
 */
public class DBConfigUtil {

    private final static Logger logger = LoggerFactory.getLogger(DBConfigUtil.class);

    public static final String DATABASE_NAME = "databaseName";
    public static final String SERVER_NAME = "serverName";
    public static final String USER = "user";
    public static final String URL_OPTIONS = "urlOptions";
    public static final String PASSWORD = "password";
    public static final String PROTOCOL = "protocol";
    public static final String PORT = "port";

    //jdbc:mysql://localhost:3306/proposal
    private static final String QUESTION_MARK = "?";

    private final static Map<String, Map<String, String>> dbConfigInfoMap = new HashMap<String, Map<String, String>>();

    public static Map<String, String> extractDatabaseConnectionInfo(String configName, String dbURLStr) {
        return extractDatabaseConnectionInfo(configName, dbURLStr, false);
    }

    public static Map<String, String> extractDatabaseConnectionInfo(String configName, String dbURLStr, boolean useCached) {
        Map<String, String> dbInfo = null;
        if (useCached) {
            dbInfo = dbConfigInfoMap.get(configName);
        }
        if (null == dbInfo) {
            try {
                dbInfo = extractDBInfo(dbURLStr);
                if (useCached){
                    dbConfigInfoMap.put(configName, dbInfo);
                }
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Error parsing url String => " + dbURLStr, e);
            }
        }
        return dbInfo;
    }

    public static Map<String, String> extractDBInfo(String dbURLStr) throws MalformedURLException {
        Map<String, String> dbInfo = new HashMap<String, String>();
        if (null != dbURLStr) {
            URL url = new URL(null, dbURLStr, new DatabaseURLHandler());
            logger.debug("url.getPath() {}", url.getPath());
            logger.debug("url.getHost() {}", url.getHost());
            logger.debug("url.getUserInfo() {}", url.getUserInfo());
            dbInfo.put(PROTOCOL, url.getProtocol());
            dbInfo.put(PORT, String.valueOf(url.getPort()));
            if (null != url.getQuery()) {
                dbInfo.put(URL_OPTIONS, url.getQuery());
            }
            dbInfo.put(DATABASE_NAME, url.getPath().substring(1));
            dbInfo.put(SERVER_NAME, url.getHost());
            if (null != url.getUserInfo()){
                String[] userInfo = url.getUserInfo().split(":");
                dbInfo.put(USER, userInfo[0]);
                dbInfo.put(PASSWORD, userInfo[1]);
            }

        } else {
            throw new IllegalArgumentException("Cannot pass null db url ");
        }
        return dbInfo;
    }

    public static String createJdbcUrl(String configName, String dbURLStr){
        return createJdbcUrl(configName,dbURLStr, false);
    }

    public static String createJdbcUrl(String configName, String dbURLStr, boolean usecached) {
        Map<String, String> dbInfo = extractDatabaseConnectionInfo(configName, dbURLStr, usecached);
        String urlOptions = dbInfo.get(URL_OPTIONS);
        StringBuilder jdbcUrl = new StringBuilder("jdbc:");
        jdbcUrl.append(dbInfo.get(PROTOCOL)).append("://");
        jdbcUrl.append(dbInfo.get(SERVER_NAME)).append(":");
        jdbcUrl.append(dbInfo.get(PORT)).append("/");
        jdbcUrl.append(dbInfo.get(DATABASE_NAME));
        if (null != urlOptions) {
            jdbcUrl.append(QUESTION_MARK).append(dbInfo.get(URL_OPTIONS));
        }
        return jdbcUrl.toString();
    }
}
