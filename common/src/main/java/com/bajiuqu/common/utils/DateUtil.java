package com.bajiuqu.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 全局时间处理类
 *
 * @author 小艺小艺
 */
public class DateUtil {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * LocalDateTime 格式转换 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date from = Date.from(zonedDateTime.toInstant());
        return from;
    }

    /**
     * Date 格式转换 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime 转换 时间字符串
     *
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToStr(LocalDateTime localDateTime) {
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String strDate = localDateTime.format(ofPattern);
        return strDate;
    }

    /**
     * 时间格式字符串转换 LocalDateTime
     *
     * @param strDate
     * @return
     */
    public static LocalDateTime strTolocalDateTime(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse(strDate, dateTimeFormatter);
        return localDateTime;
    }

    /**
     * 时间格式字符串转换 LocalDate
     *
     * @param strDate
     * @return
     */
    public static LocalDate strTolocalDate(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate localDateTime = LocalDate.parse(strDate, dateTimeFormatter);
        return localDateTime;
    }

    /**
     * 时间戳转化时间格式字符串
     *
     * @param timestamp
     * @return
     */
    public static String timestampToString(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        String strDate = localDateTimeToStr(localDateTime);
        return strDate;
    }

    /**
     * 时间字符串转换 Date
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        LocalDateTime localDateTime = strTolocalDateTime(strDate);
        Date date = localDateTimeToDate(localDateTime);
        return date;
    }

    /**
     * Date 转换 字符串时间
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        String strDate = localDateTimeToStr(localDateTime);
        return strDate;
    }

    /**
     * 获取今日起始时间
     *
     * @return
     */
    public static Date getStartTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.of(0, 0, 0);
        LocalDateTime of1 = LocalDateTime.of(localDate, localTime);
        Date date = localDateTimeToDate(of1);
        return date;
    }

    /**
     * 将时间转换为时间戳
     *
     * @param date
     * @return Date
     */
    public static int dateToInt(Date date) {
        int dateI = (int) (date.getTime() / 1000);
        return dateI;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param start
     * @param end
     * @return
     */
    public static double getDistanceOfTwoDate(Date start, Date end) {
        long beforeTime = start.getTime();
        long afterTime = end.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param startInclusive 开始包含时间 --- 必须包含时分秒
     * @param endExclusive   结束独占时间 --- 必须包含时分秒
     * @return
     */
    public static long dateInterval(Date startInclusive, Date endExclusive) {
        LocalDateTime localDateTimeStart = dateToLocalDateTime(startInclusive);
        LocalDateTime localDateTimeEnd = dateToLocalDateTime(endExclusive);
        long between = Duration.between(localDateTimeStart, localDateTimeEnd).toDays();
        return between;
    }

}
