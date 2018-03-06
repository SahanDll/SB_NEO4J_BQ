package com.dev.db.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by N5608296 on 14/02/2018 014.
 */
public class Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static String getDatePortion(int date) {
        String datePortion = null;
        Calendar calendar = null;
        try {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, date);
            datePortion = sdf.format(calendar.getTime());
        } catch (Exception e) {
            LOGGER.error("{\"error\" : \"" + e.getMessage() + "\"}");
        }
        return datePortion;
    }

    public static Date getBackDate(int date) {
        Calendar calendar = null;
        try {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, date);
        } catch (Exception e) {
            LOGGER.error("{\"error\" : \"" + e.getMessage() + "\"}");
        }
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    private static Date localDateTimeToDate(LocalDateTime startOfDay) {
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }
}
