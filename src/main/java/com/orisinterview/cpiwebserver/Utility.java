package com.orisinterview.cpiwebserver;

import java.util.Calendar;

public class Utility {
    public static long getCurrentTimeInMillis() {
        return System.currentTimeMillis();
    }

    public static long generateOneDayExpiry() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getCurrentTimeInMillis());

        calendar.add(Calendar.DATE, 1);

        return calendar.getTimeInMillis();
    }
}
