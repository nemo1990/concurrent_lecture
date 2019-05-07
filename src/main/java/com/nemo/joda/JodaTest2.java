package com.nemo.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

public class JodaTest2 {

    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

//        System.out.println(today);
        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));

        System.out.println("----------");

        DateTime d1 = today.withDayOfMonth(1);

        System.out.println(d1);

        System.out.println("----------");

        //当前时区日期
        LocalDate localDate = new LocalDate();
        System.out.println("??? " + localDate);

        System.out.println("----------");

        LocalTime localTime = new LocalTime();
        System.out.println("--- " + localTime);


        //当前一天后三个月的最大天数日期
        localDate = localDate.plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);

        System.out.println("----------");

        //计算两年前第三个月最后的日期
        DateTime dateTime = new DateTime();
        DateTime dateTime2 = dateTime.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMinimumValue();
        System.out.println(dateTime2.toString("yyyy-MM-dd"));


        DateTime d = DateTime.parse("2014-11-04T09:22:54.867Z", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        System.out.println(d);

        DateTime d2 = new DateTime(DateTimeZone.UTC);
        System.out.println(d2);

    }
}
