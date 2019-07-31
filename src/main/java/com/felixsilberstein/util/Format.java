package com.felixsilberstein.util;

import java.sql.Timestamp;
import java.util.Date;

public class Format {
    public static Timestamp Date2MySQLTimestamp(Date dt) {
        return new Timestamp(dt.getTime());
    }
}
