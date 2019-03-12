package com.csu.lab.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomUtils {
    public static String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    public static Date getCustomTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        Date date = calendar.getTime();
        String dateStringParse = sdf.format(date);

//        String dateString = "2017-12-20 14:02:08";
        try {
            Date dateParse = sdf.parse(dateStringParse);
//            message.setCreateTime(dateParse);
            return dateParse;
        } catch (ParseException e) {
//            System.out.println("字符串解析错误");
//            throw new RuntimeException("");
            e.printStackTrace();
        }
        return null;
    }

}
