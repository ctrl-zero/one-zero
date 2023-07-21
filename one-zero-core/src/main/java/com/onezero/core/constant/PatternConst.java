package com.onezero.core.constant;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * {@code @author} voncho
 * {@code @desc} 时间相关常量
 */
public interface PatternConst {
    String DATE_FORMAT = "yyyy-MM-dd";
    String INT_DATE_FORMAT = "yyyyMMdd";
    String INT_DATE_HOUR_FORMAT = "yyyyMMddHH";
    String INT_DATE_HOUR_SECOND_FORMAT = "yyyyMMddHHmmss";
    String MONTH_FORMAT = "yyyy-MM";
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String TIME_FORMAT = "HH:mm:ss";
    String DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    List<String> DATETIME_FORMATS = List.of(DATETIME_FORMAT, DATETIME_FORMAT, TIME_FORMAT);
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    DateTimeFormatter INT_DATE_FORMATTER = DateTimeFormatter.ofPattern(INT_DATE_FORMAT);
    DateTimeFormatter INT_DATE_HOUR_FORMATTER = DateTimeFormatter.ofPattern(INT_DATE_HOUR_FORMAT);
    DateTimeFormatter DATETIME_MINUTE_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_MINUTE_FORMAT);
    DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern(MONTH_FORMAT);
    DateTimeFormatter DATE_HOUR_SECOND_FORMAT = DateTimeFormatter.ofPattern(INT_DATE_HOUR_SECOND_FORMAT);
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
    DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
    List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(DATE_TIME_FORMATTER, DATETIME_MINUTE_FORMATTER, TIME_FORMATTER);
}
