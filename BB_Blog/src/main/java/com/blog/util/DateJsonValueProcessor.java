package com.blog.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author 6JIE
 * @create 2020-04-21 17:45
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private String format;

    public DateJsonValueProcessor(String format){
        this.format=format;
    }
    @Override
    public Object processArrayValue(Object args, JsonConfig arg1) {
        return null;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if(value==null){
            return "";
        }
        if(value instanceof Timestamp){
            String str = new SimpleDateFormat(this.format).format((Timestamp)value);
        }
        if(value instanceof Timestamp){
            String str = new SimpleDateFormat(this.format).format((Timestamp)value);
        }
        return value.toString();
    }
}
