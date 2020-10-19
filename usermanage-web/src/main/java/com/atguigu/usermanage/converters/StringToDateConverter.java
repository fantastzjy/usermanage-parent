package com.atguigu.usermanage.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConverter implements Converter<String, Date> {
    
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        
        if (StringUtils.isEmpty(source)) {
            return null;
        } else {
            try {
                return FORMATTER.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
