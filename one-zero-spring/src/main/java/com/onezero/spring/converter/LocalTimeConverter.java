package com.onezero.spring.converter;

import com.onezero.core.constant.PatternConst;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeConverter implements Converter<String, LocalTime> {
    private static DateTimeFormatter DF = DateTimeFormatter.ofPattern(PatternConst.TIME_FORMAT);
    @Override
    public LocalTime convert(@NonNull String source) {
        return LocalTime.parse(source, DF);
    }
}
