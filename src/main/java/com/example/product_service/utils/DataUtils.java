package com.example.product_service.utils;


import org.springframework.util.StringUtils;

public class DataUtils {
    public static  String makeLikeStr (String str) {
        if (StringUtils.isEmpty(str)) return str;
        str = str.trim().toLowerCase()
                .replace("\\", "\\\\")
                .replace("\\t", "\\\\t")
                .replace("\\n", "\\\\n")
                .replace("\\r", "\\\\r")
                .replace("\\z", "\\\\z")
                .replace("\\b", "\\\\b")
                .replace("&", Constants.DEFAULT_ESCAPE_CHAR + "&")
                .replace("%", Constants.DEFAULT_ESCAPE_CHAR + "%")
                .replace("_", Constants.DEFAULT_ESCAPE_CHAR + "_");
        return "%" + str + "%";
    }

    public static boolean isNullOrEmpty (final Object obj) {
        return obj == null || obj.toString().isEmpty();
    }

    public static Long safeToLong (Object obj) {
        return safeToLong(obj, null);
    }

    public static Long safeToLong (Object obj, Long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        try {
            return Long.valueOf(obj.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String safeToString (Object obj, String defaultvalua) {
        if (obj == null) return defaultvalua;
        if (obj instanceof String) return (String) obj;
        return obj.toString();
    }

    public static String safeToString (Object obj){
        return safeToString(obj, null);
    }
}
