package com.example.product_service.utils;

import javax.annotation.Nonnull;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class DateUtils {
    public static final class Format {
        public Format() {
        }
        public static final String DD_MM_YYYY = "dd/MM/yyyy";
    }

    public static Date safeToDate (String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            return sdf.parse(dateStr);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Instant safeToInstant (@Nonnull Object obj, @Nonnull String format) {
        try {
            return Objects.requireNonNull(DateUtils.safeToDate(DataUtils.safeToString(obj), format)).toInstant();
        } catch (Exception ex) {
            return null;
        }
    }

    public static Instant safeToInstant (Object obj) {
        return safeToInstant(obj, Format.DD_MM_YYYY);
    }
}
