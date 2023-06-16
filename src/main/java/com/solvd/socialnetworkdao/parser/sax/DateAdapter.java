package com.solvd.socialnetworkdao.parser.sax;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {
    @Override
    public Date unmarshal(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = simpleDateFormat.parse(string);
        } catch (ParseException e) {
            return null;
        }
        return new Date(date.getTime());
    }

    @Override
    public String marshal(Date date) {
        return date.toString();
    }
}
