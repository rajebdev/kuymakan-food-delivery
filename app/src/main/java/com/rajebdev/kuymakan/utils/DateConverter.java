package com.rajebdev.kuymakan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

    public String convert(String date){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, HH:mm");
        String output = null;
        try {
            output = formatter.format(parser.parse(date.split("\\+")[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }
}
