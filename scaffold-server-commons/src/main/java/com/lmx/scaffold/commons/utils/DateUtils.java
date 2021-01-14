
package com.lmx.scaffold.commons.utils;



import com.lmx.scaffold.commons.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang.time.DateUtils.add;

/**
 * date utils
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * <code>java.util.Date</code> to <code>java.time.LocalDateTime</code>
     * use default zone
     *
     * @param date
     * @return
     */
    private static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * <code>java.time.LocalDateTime</code> to <code>java.util.Date</code>
     * use default zone
     *
     * @param localDateTime
     * @return
     */
    private static Date localDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * @return get the formatted date string for the current time
     */
    public static String getCurrentTime() {
        return getCurrentTime(Constants.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param format format
     * @return get the date string in the specified format of the current time
     */
    public static String getCurrentTime(String format) {
//        return new SimpleDateFormat(format).format(new Date());
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param date   date
     * @param format e.g. yyyy-MM-dd HH:mm:ss
     * @return get the formatted date string
     */
    public static String format(Date date, String format) {
//        return new SimpleDateFormat(format).format(date);
        return format(date2LocalDateTime(date), format);
    }

    /**
     * @param localDateTime local data time
     * @param format        e.g. yyyy-MM-dd HH:mm:ss
     * @return get the formatted date string
     */
    public static String format(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param date date
     * @return convert time to yyyy-MM-dd HH:mm:ss format
     */
    public static String dateToString(Date date) {
        return format(date, Constants.YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * @param date   date
     * @param format format
     * @return convert string to date and time
     */
    public static Date parse(String date, String format) {
        try {
            //     return new SimpleDateFormat(format).parse(date);
            LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            return localDateTime2Date(ldt);
        } catch (Exception e) {
            logger.error("error while parse date:" + date, e);
        }
        return null;
    }


    /**
     * convert date str to yyyy-MM-dd HH:mm:ss format
     *
     * @param str date string
     * @return yyyy-MM-dd HH:mm:ss format
     */
    public static Date stringToDate(String str) {
        return parse(str, Constants.YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * convert date str to yyyy-MM-dd HH:mm:ss format
     *
     * @param str date string
     * @return yyyy-MM-dd HH:mm:ss format
     */
    public static LocalDateTime stringToLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(Constants.YYYY_MM_DD_HH_MM_SS));
    }


    /**
     * get seconds between two dates
     *
     * @param d1 date1
     * @param d2 date2
     * @return differ seconds
     */
    public static long differSec(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        return (long) Math.ceil(differMs(d1, d2) / 1000.0);
    }

    /**
     * get ms between two dates
     *
     * @param d1 date1
     * @param d2 date2
     * @return differ ms
     */
    public static long differMs(Date d1, Date d2) {
        return Math.abs(d1.getTime() - d2.getTime());
    }


    /**
     * get hours between two dates
     *
     * @param d1 date1
     * @param d2 date2
     * @return differ hours
     */
    public static long diffHours(Date d1, Date d2) {
        return (long) Math.ceil(diffMin(d1, d2) / 60.0);
    }

    /**
     * get minutes between two dates
     *
     * @param d1 date1
     * @param d2 date2
     * @return differ minutes
     */
    public static long diffMin(Date d1, Date d2) {
        return (long) Math.ceil(differSec(d1, d2) / 60.0);
    }


    /**
     * get the date of the specified date in the days before and after
     *
     * @param date date
     * @param day  day
     * @return the date of the specified date in the days before and after
     */
    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * compare two dates
     *
     * @param future future date
     * @param old    old date
     * @return true if future time greater than old time
     */
    public static boolean compare(Date future, Date old) {
        return future.getTime() > old.getTime();
    }

    /**
     * convert schedule string to date
     *
     * @param schedule schedule
     * @return convert schedule string to date
     */
    public static Date getScheduleDate(String schedule) {
        return stringToDate(schedule);
    }

    /**
     * format time to readable
     *
     * @param ms ms
     * @return format time
     */
    public static String format2Readable(long ms) {

        long days = ms / (1000 * 60 * 60 * 24);
        long hours = (ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (ms % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (ms % (1000 * 60)) / 1000;

        return String.format("%02d %02d:%02d:%02d", days, hours, minutes, seconds);

    }

    /**
     * get monday
     * <p>
     * note: Set the first day of the week to Monday, the default is Sunday</p>
     *
     * @param date date
     * @return get monday
     */
    public static Date getMonday(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return cal.getTime();
    }

    /**
     * get sunday
     * <p>
     * note: Set the first day of the week to Monday, the default is Sunday
     *
     * @param date date
     * @return get sunday
     */
    public static Date getSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return cal.getTime();
    }

    /**
     * get first day of yea
     *
     * @param date date
     * @return first day of month
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, 1);

        return cal.getTime();
    }

    /**
     * get last day of year
     *
     * @param date date
     * @return get last day of month
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.YEAR, 1);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);

        return cal.getTime();
    }


    /**
     * get first day of month
     *
     * @param date date
     * @return first day of month
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     * get the date of the specified date in the years before and after
     *
     * @param date date
     * @param year year
     * @return the date of the specified date in the days before and after
     */
    public static Date getSomeDayofYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * get the date of the specified date in the month before and after
     *
     * @param date  date
     * @param month month
     * @return the date of the specified date in the days before and after
     */
    public static Date getSomeDayofMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * get the date of the specified date in the month before and after
     *
     * @param date date
     * @param hour hour
     * @return the date of the specified date in the days before and after
     */
    public static Date getSomeDayofHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * get the date of the specified date in the minute before and after
     *
     * @param date   date
     * @param minute minute
     * @return the date of the specified date in the days before and after
     */
    public static Date getSomeDayofMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * get some hour of day
     *
     * @param date  date
     * @param hours hours
     * @return some hour of day
     */
    public static Date getSomeHourOfDay(Date date, int hours) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - hours);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * get last day of month
     *
     * @param date date
     * @return get last day of month
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        return cal.getTime();
    }

    /**
     * return YYYY-MM-DD 00:00:00
     *
     * @param inputDay date
     * @return start day
     */
    public static Date getStartOfDay(Date inputDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * return YYYY-MM-DD 23:59:59
     *
     * @param inputDay day
     * @return end of day
     */
    public static Date getEndOfDay(Date inputDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * return YYYY-MM-DD 00:00:00
     *
     * @param inputDay day
     * @return start of hour
     */
    public static Date getStartOfHour(Date inputDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDay);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * return YYYY-MM-DD 23:59:59
     *
     * @param inputDay day
     * @return end of hour
     */
    public static Date getEndOfHour(Date inputDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDay);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * return YYYY-MM-DD 00:00:00
     *
     * @param inputDay day
     * @return start of hour
     */
    public static Date getStartOfMinute(Date inputDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDay);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * return YYYY-MM-DD 23:59:59
     *
     * @param inputDay day
     * @return end of hour
     */
    public static Date getEndOfMinute(Date inputDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDay);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * Adds a number of days to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * parse localdatetime to date
     *
     * @param localDateTime
     * @return
     */
    public static Date parse(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间戳 秒级别
     * @param date
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }


}
