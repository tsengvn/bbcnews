package me.hienngo.bbcsport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class DateTimeUtil {
    private static final SimpleDateFormat API_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm aaa, MMM dd yyyy", Locale.getDefault());

    public static String parseTimestamp(String value){
        try {
            return DATE_FORMAT.format(API_DATE_FORMAT.parse(value));
        } catch (ParseException e) {
            e.printStackTrace();
            return value;
        }
    }

}
