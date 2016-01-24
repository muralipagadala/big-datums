package com.bigdatums.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BdTimeUtils {

    public static Integer tsToSec8601(String timestamp){
        if(timestamp == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date dt = sdf.parse(timestamp);
            long epoch = dt.getTime();
            return (int)(epoch/1000);
        } catch(ParseException e) {
            return null;
        }
    }

    public static void main(String[] args){
        System.out.println(BdTimeUtils.tsToSec8601("2016-01-01T00:00:00.000-0000"));
    }

}
