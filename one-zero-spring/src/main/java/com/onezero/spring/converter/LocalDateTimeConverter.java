package com.onezero.spring.converter;

import com.onezero.core.constant.PatternConst;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(@NonNull String source) {
        LocalDateTime localDateTime = null;
        for (DateTimeFormatter dateTimeFormatter : PatternConst.DATE_TIME_FORMATTERS) {
            try {
                localDateTime = LocalDateTime.parse(source, dateTimeFormatter);
            } catch (Exception ignore) {
            }
            if (localDateTime != null) {
                break;
            }
        }
        return localDateTime;
    }
}
