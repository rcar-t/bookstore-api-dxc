package com.rhonda.bookstoredemo.utils;

import io.netty.util.internal.logging.FormattingTuple;
import io.netty.util.internal.logging.MessageFormatter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class Util {

    public String format(String format,String...params){
        FormattingTuple ft = MessageFormatter.arrayFormat(format,params);
        return ft.getMessage();
    }

    public Boolean isValid(Optional<String> str) {
        return str.isPresent() && !str.get().isBlank();
    }
}
