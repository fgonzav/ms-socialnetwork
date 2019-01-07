package cl.social.util;

import com.google.gson.GsonBuilder;

public class JsonUtil {

    private static final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";
    
    private JsonUtil() {
        super();
    }
    
    public static String toJson(Object object) {
        return new GsonBuilder().setDateFormat(DATE_FORMAT).serializeNulls().create().toJson(object);
    }
}
