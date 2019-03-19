package com.csu.lab.utils;

import com.csu.lab.customConst.CustomConstant;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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

    public static String uploadFile(MultipartFile blFile) throws IOException {
        String oldFileName = blFile.getOriginalFilename();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + CustomConstant.VIDEO_SAVE_PATH;
        String randomStr = UUID.randomUUID().toString();
        String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
        File file = new File(path, newFileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        blFile.transferTo(file);
        return path + "/" + newFileName;
//        project.setVideo();
    }

    public static void deleteFile(String filename) {
        if (filename != null && !filename.equals("")) {
            File deleteFile = new File(filename);
            if (deleteFile != null
                    && deleteFile.exists() && deleteFile.isFile()) {
                deleteFile.delete();
            }
        }

    }

}
