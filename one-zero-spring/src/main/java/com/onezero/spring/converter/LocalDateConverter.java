package com.onezero.spring.converter;

import com.onezero.core.constant.PatternConst;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(@NonNull String source) {
        return LocalDate.parse(source, PatternConst.DATE_FORMATTER);
    }
}
