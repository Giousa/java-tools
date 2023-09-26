package com.giousa.tools.time;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    private static int PRELOAD_TIME_PERIOD = 300;
    private static TimeUnit PERIOD_UNIT = TimeUnit.SECONDS;

    /**
     * 校验，减去某时间后，是否大于当前时间
     * true:   大于或等于当前时间
     * false： 小于当前时间
     */
    private static boolean lazyLoad2(Date actionTime) {
        return new DateTime(actionTime).minus(PERIOD_UNIT.toMillis(PRELOAD_TIME_PERIOD)).compareTo(new DateTime()) >= 0;
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

    public static void main(String[] args) {
        Date date = DateUtils.stringToDate("2023-09-08 14:15:49",null);
        long time1 = date.getTime();
        System.out.println("current >>> "+time1);

        DateTime minus = new DateTime(date).minus(300000);
        long time2 = minus.toDate().getTime();
        System.out.println("after >>> "+time2);

        System.out.println("minus >>> "+(time2-time1));

        System.out.println("lazyLoad >>> "+lazyLoad2(date));
    }
}
