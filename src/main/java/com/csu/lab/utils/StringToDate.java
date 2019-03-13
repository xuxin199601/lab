package com.csu.lab.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;

@Component
public class StringToDate implements Converter<String,Timestamp> {
    @Override
    public Timestamp convert(String s) {

        Timestamp sql = Timestamp.valueOf(s);

        return sql;
    }

    public static Timestamp tringToDate(String s) {
        Timestamp sql = Timestamp.valueOf(s);
        return sql;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }


}