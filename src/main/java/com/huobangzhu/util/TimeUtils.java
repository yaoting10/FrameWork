package com.huobangzhu.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils{

    public static final long currentMillis(){
        return Clock.systemUTC().millis();
    }

    public static final long plusMonths(long instant, long months){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusMonths(months).toInstant().toEpochMilli();
    }

    public static final long subMonths(long instant, long months){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).minusMonths(months).toInstant().toEpochMilli();
    }

    public static final long plusDays(long instant, long days){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusDays(days).toInstant().toEpochMilli();
    }

    public static final long plusSeconds(long instant, long seconds){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusSeconds(seconds).toInstant().toEpochMilli();
    }

    public static final long plusMinutes(long instant, long minutes){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusMinutes(minutes).toInstant().toEpochMilli();
    }

    public static final LocalDate millisToDate(long millis){
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static final long countDays(long deltaMillis) {
        return Duration.ofMillis(deltaMillis).toDays();
    }

    public static final int getDayOfMonth(long instant) {
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).getDayOfMonth();
    }

    public static final boolean isInTheSameDay(long standardMillis, long millis) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateTimeFormatter.format(new Date(standardMillis)));
        System.out.println(dateTimeFormatter.format(new Date(millis)));
        ZonedDateTime timeInstance = Instant.ofEpochMilli(standardMillis).atZone(ZoneId.systemDefault());
        timeInstance = timeInstance.minusHours(timeInstance.getHour());
        timeInstance = timeInstance.minusMinutes(timeInstance.getMinute());
        timeInstance = timeInstance.minusSeconds(timeInstance.getSecond());
        timeInstance = timeInstance.minusNanos(timeInstance.getNano());
        long standardDayMillis = timeInstance.toInstant().toEpochMilli();
        return countDays(millis - standardDayMillis) == 0;
    }

    public static final String millisToDateTime(Long millis, String pertten){
        LocalDateTime time = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return time.format(DateTimeFormatter.ofPattern(pertten));
    }

    public static final Map<String, Long> getCurrentDayBeginAndEnd(String currentDay, String separator){
        Long beg = null;
        Long end = null;
        Map<String, Long> timeMap = new HashMap<>();
        if (StringUtils.isNotBlank(currentDay) && StringUtils.isNotBlank(separator)) {
            try{
                String[] dates = currentDay.split(separator);
                beg = ModelUtils.parseToMillis(dates[0] + " 00:00:00:000");
                end = ModelUtils.parseToMillis(dates[1] + " 23:59:59:999");
                timeMap.put("beg", beg);
                timeMap.put("end", end);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            return timeMap;
        }
        return null;
    }
}
