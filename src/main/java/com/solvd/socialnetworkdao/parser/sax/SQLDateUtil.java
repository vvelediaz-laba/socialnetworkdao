package com.solvd.socialnetworkdao.parser.sax;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SQLDateUtil {
    public static Date toSQLDate(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return new Date(Long.valueOf(s));
        }
        return new Date(date.getTime());
    }
}
