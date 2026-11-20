package org.sebsy.exercices;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {

    private static final DateTimeFormatter FORMAT_PAR_DEFAUT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateUtils() {
    }

    public static String convertDate(Date date) {
        return format(date, FORMAT_PAR_DEFAUT);
    }

    public static String convertDate(Date date, String pattern) {
        return format(date, DateTimeFormatter.ofPattern(pattern));
    }

    // Date reste dans la signature pour les appelants existants ; java.time fait le travail, sans
    // état partagé. Un java.sql.Date lèverait UnsupportedOperationException sur toInstant().
    private static String format(Date date, DateTimeFormatter formatter) {
        return date.toInstant().atZone(ZoneId.systemDefault()).format(formatter);
    }
}
