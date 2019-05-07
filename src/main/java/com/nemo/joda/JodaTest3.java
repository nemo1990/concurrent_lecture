package com.nemo.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class JodaTest3 {

    //标准UTC时间：2014-11-04T09:22:54.867Z

    /**
     *
     * @param utcDate 客户端发来的标准UTC时间
     * @return 服务端解析的当地时间
     */
    public static Date convertUTC2Date(String utcDate) {
        try {
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * @param javaDate 服务端的时间
     * @return 转换标准UTC时间发给客户端
     */
    public static String convertDate2UTC(Date javaDate) {
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    public static String convertDate2LocalByDateFormat(Date javaDate, String dateFormat) {
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }


    public static void main(String[] args) {
        System.out.println(JodaTest3.convertUTC2Date("2014-11-04T09:22:54.867Z"));
        System.out.println(JodaTest3.convertDate2UTC(new Date()));
        System.out.println(JodaTest3.convertDate2LocalByDateFormat(new Date(), "yyyy-MM-dd'T'HH:mm:ss"));


    }


}
