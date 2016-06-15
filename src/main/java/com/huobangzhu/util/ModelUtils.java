package com.huobangzhu.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelUtils{

    public static final String getSerial(){
        return UUID.randomUUID().toString();
    }

    public static final String getRechargeSerial(){
        return RandomStringUtils.random(5, 48, 58, false, true) + TimeUtils.currentMillis();
    }

    public static final String getEmptyStr(String s){
        return StringUtils.isBlank(s) ? "" : s;
    }

    public static final long parseToMillis(String formatDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date dt = sdf.parse(formatDate);
        return dt.getTime();
    }

    public static final long parseToDate(String formatDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(formatDate.contains("年")||formatDate.contains("月")||formatDate.contains("日")){
            sdf = new SimpleDateFormat("yyyy年MM月dd日");
            Date dt = sdf.parse(formatDate);
            return dt.getTime();
        }
        if(formatDate.contains("/")){
            sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date dt = sdf.parse(formatDate);
            return dt.getTime();
        }
        Date dt = sdf.parse(formatDate);
        return dt.getTime();
    }

    public static final long getBeforeDaysMillis(long l) throws ParseException{
        return parseToMillis(getBeforeDays(l).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
    }

    public static final LocalDateTime getBeforeDays(long l){
        return LocalDateTime.of(LocalDate.now().minus(l, ChronoUnit.DAYS), LocalTime.MIN);
    }

    public static final long getBeforeMonthsMillis(long l) throws ParseException{
        return parseToMillis(getBeforeMonths(l).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
    }

    public static final LocalDateTime getBeforeMonths(long l){
        return LocalDateTime.of(LocalDate.now().minus(l, ChronoUnit.MONTHS),LocalTime.MIN);
    }

    public static final String randomString(int i){
        char[] cs = new char[i];
        String baseStr = "0123456789";
        int total = baseStr.length();
        Random random = new Random();
        for(int j = 0; j < cs.length; j++){
            cs[j] = baseStr.charAt(random.nextInt(total));
        }
        return String.valueOf(cs);
    }



    public static final long currentMillis(){
        return Instant.now().toEpochMilli();
    }
}
