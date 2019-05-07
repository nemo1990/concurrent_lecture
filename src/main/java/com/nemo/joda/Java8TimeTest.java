package com.nemo.joda;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

public class Java8TimeTest {

    public static void main(String[] args) {
        //关注日期年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        System.out.println(localDate.getYear() + ", " + localDate.getMonthValue() + ", " + localDate.getDayOfMonth());

        System.out.println("-------");

        LocalDate localDate2 = LocalDate.of(2017, 3, 3);
        System.out.println(localDate2);

        System.out.println("-------");

        LocalDate localData3 = LocalDate.of(2010, 3, 25);
        //只关注几月几日
        MonthDay monthDay = MonthDay.of(localData3.getMonth(), localData3.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2011, 3, 25));

//        System.out.println(monthDay);
        if(monthDay.equals(monthDay2)) {
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }
        System.out.println("-------");

        //关注时分秒
        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime time2 = time.plusHours(3).plusMinutes(20);
        System.out.println(time2);

        System.out.println("-------");

        LocalDate localDate1 = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(localDate1);

        System.out.println("-------");

        LocalDate localDate4 = localDate.minus(2, ChronoUnit.MONTHS);
        System.out.println(localDate4);

        System.out.println("--------");

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());

        System.out.println("--------");

        LocalDate localDate5 = LocalDate.now();
        LocalDate localDate6 = LocalDate.of(2017, 3, 19);

        System.out.println(localDate5.isAfter(localDate6));
        System.out.println(localDate5.isBefore(localDate6));
        System.out.println(localDate5.equals(localDate6));

        System.out.println("--------");

        Set<String> set = ZoneId.getAvailableZoneIds();
        //排序
        Set<String> treeSet = new TreeSet<String>() {
            {
                addAll(set);
            }
        };
//        treeSet.forEach(System.out::println);

        System.out.println("-------?");

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(localDateTime);
        System.out.println(zonedDateTime);

        System.out.println("--------");

        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());

        System.out.println("--------");

        YearMonth yearMonth1 = YearMonth.of(2016, 2);
        System.out.println(yearMonth1);
        System.out.println(yearMonth1.lengthOfMonth());
        System.out.println(yearMonth1.lengthOfYear());
        System.out.println(yearMonth1.isLeapYear());

        System.out.println("--------");

        LocalDate localDate7 = LocalDate.now();
        LocalDate localDate8 = LocalDate.of(2018, 5, 24);

        Period period = Period.between(localDate7, localDate8);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

        System.out.println("--------");

        //不带时区标准UTC
        System.out.println(Instant.now());

        System.out.println(Instant.ofEpochSecond(3).getEpochSecond());
        System.out.println(Instant.ofEpochSecond(2, 1000000000));


        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 2, 0, 0, 0);
        Duration duration = Duration.between(localDateTime, localDateTime1);
        System.out.println(duration);

//        ZoneId roomZone = ZoneId.of("Europe/Rome");
//        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//        Instant instantFromDateTime = dateTime.toInstant(roomZone);

    }

}
