package com.csu.lab.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {

    public static void downloadFile( String fileUrl,HttpServletResponse response){

        //返回的文件中文，需要设置一下
        String fileName=fileUrl.split("/")[fileUrl.split("/").length - 1];
        // 设置返回流到客户端
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");

        try{
            fileName=java.net.URLEncoder.encode(fileName, "UTF-8");
        }catch (UnsupportedEncodingException e){

        }

        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        File file = new File(fileUrl);
        if(file.exists()==false){
            throw new RuntimeException("下载文件不存在");
        }

        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件下载出错，返回文件流出错！");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
