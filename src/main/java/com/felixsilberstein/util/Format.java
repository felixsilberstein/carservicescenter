package com.felixsilberstein.util;

import java.util.Date;

public class Format {
    public static String MySQLDate(Date dt) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
}
