package com.giousa.tools.time;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DateUtils {

    public static final String DATE_FORAMT = "yyyy-MM-dd";
    public static final String TIME_FORAMT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = DATE_FORAMT + " " + TIME_FORAMT;

    public static Date asDate(LocalDate localDate) {
        return Objects.isNull(localDate) ? null : Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDate) {
        return Objects.isNull(localDate) ? null : Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String format(LocalDate date, String pattern) {
        if (date == null || StringUtils.isBlank(pattern)) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(Date date, String format) {
        if (date == null || format == null || "".equals(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String format(Date date, String format, Locale locale) {
        if (date == null || format == null || "".equals(format)) {
            return null;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String dateStr, String format) {
        if (StringUtils.isAnyBlank(dateStr, format)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据生日计算月龄
     *
     * @param birthday 生日
     * @return 月龄，单位为月
     */
    public static Integer calculateAge(LocalDate birthday) {
        if (birthday == null) {
            return 0;
        }
        LocalDate now = LocalDate.now();
        int months = (int) ChronoUnit.MONTHS.between(birthday, now);
        int days = (int) ChronoUnit.DAYS.between(birthday.plusMonths(months), now);
        return days > 0 ? months + 1 : months;
    }

    /**
     * 根据生日计算日龄
     *
     * @param birthday 生日
     * @return 日龄，单位为日
     */
    public static Integer calculateDayAge(LocalDate birthday) {
        return birthday == null ? 0 : (int) ChronoUnit.DAYS.between(birthday, LocalDate.now());
    }

    /**
     * 根据生日计算岁
     *
     * @param birthday
     * @return
     */
    public static Integer calculateYearAge(LocalDate birthday) {
        return birthday == null ? null : (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    /**
     * 根据生日计算岁
     *
     * @param birthday 生日
     * @return 年龄，单位为年
     */
    public static Integer calculateYearAge(Date birthday) {
        return birthday == null ? null : (int) ChronoUnit.YEARS.between(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
    }

    public static long timeToNow(long start) {
        if (start <= 0) return 0;
        long time = (System.currentTimeMillis() - start) / 1000;
        return time > 0 ? time : 0;
    }

    /**
     * 比较日期大小
     * date1小于date2返回-1，date1大于date2返回1，相等返回0
     * 如：
     * date1: 2021-05-06 10:00:00
     * date2: 2021-05-06 11:00:00
     * 返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareTo(Date date1, Date date2) {
        return date1.compareTo(date2);
    }


    /**
     * long -- > string
     *
     * @return
     */
    public static String longToString(Long time, String formatType) {
        if (time == null) {
            return null;
        }
        if (StringUtils.isBlank(formatType)) {
            formatType = DATE_TIME_FORMAT;
        }
        return dateToString(longToDate(time, formatType), formatType);
    }

    /**
     * long -- > date
     *
     * @return
     */
    public static Date longToDate(Long currentTime, String formatType) {
        if (currentTime == null) {
            return null;
        }
        if (StringUtils.isBlank(formatType)) {
            formatType = DATE_TIME_FORMAT;
        }
        return stringToDate(dateToString(new Date(currentTime), formatType), formatType);
    }

    /**
     * date -- > string
     *
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        if (Objects.isNull(data)) {
            return null;
        }

        if (StringUtils.isBlank(formatType)) {
            formatType = DATE_TIME_FORMAT;
        }

        try {
            return new SimpleDateFormat(formatType).format(data);
        } catch (Exception e) {
//            logger.warn("dateToString fail. data: {}, formatType: {}, e: {}", data, formatType, e);
            return null;
        }
    }

    /**
     * string -- > date
     *
     * @return
     */
    public static Date stringToDate(String strTime, String formatType) {
        if (StringUtils.isBlank(strTime)) {
            return null;
        }
        if (StringUtils.isBlank(formatType)) {
            formatType = DATE_TIME_FORMAT;
        }
        try {
            return new SimpleDateFormat(formatType).parse(strTime);
        } catch (ParseException e) {
//            logger.warn("stringToDate fail. strTime: {}, formatType: {}, e: {}", strTime, formatType, e);
            return null;
        }
    }

    /**
     * string -- > long
     *
     * @return
     */
    public static long stringToLong(String strTime, String formatType) {
        if (StringUtils.isBlank(strTime)) {
            return 0;
        }
        if (StringUtils.isBlank(formatType)) {
            formatType = DATE_TIME_FORMAT;
        }
        Date date = stringToDate(strTime, formatType);
        if (Objects.isNull(date)) {
            return 0;
        } else {
            Long time = dateToLong(date);
            return Objects.isNull(time) ? 0 : time;
        }
    }

    /**
     * date -- > long
     *
     * @param date
     * @return
     */
    public static Long dateToLong(Date date) {
        if(Objects.isNull(date)){
            return null;
        }
        return date.getTime();
    }


    public static Integer getWeekDate(long time) {
        long l = System.currentTimeMillis() - time;
        if (l < 0) {
            return 0;
        }
        return Math.toIntExact(l / (7 * 1000 * 60 * 68 * 24));
    }

    /**
     * 追加时间
     *
     * @param date
     * @param calendarField
     * @param amount
     * @return
     */
    public static Date addDate(Date date, int calendarField, int amount) {
        try {
            if (Objects.isNull(date)) {
                date = new Date();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        } catch (Exception e) {
//            logger.warn("addDate fail. date: {},calendarField: {},amount: {},e: {}", date, calendarField, amount, e);
            return null;
        }
    }


    /**
     * date --》 LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        try {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (Exception e) {
//            logger.warn("dateToLocalDateTime fail. date: {},e: {}", date, e);
        }
        return null;
    }
}
