package com.csu.lab.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {

    /**
     * 将byte文件数组写成文件
     *
     * @param bfile
     * @param filePath
     * @param fileName
     * @throws IOException
     *
     * @author sunday
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) throws IOException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        File dir = new File(filePath);
        if (!dir.exists() && dir.isDirectory()) {
            // 判断文件目录是否存在
            dir.mkdirs();
        }
        file = new File(filePath + "\\" + fileName);
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (IOException e) {
            throw new IOException("将byte文件流保存到本地出错");
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    throw new IOException("将byte文件流保存到本地出错");
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    throw new IOException("将byte文件流保存到本地出错");
                }
            }
        }
    }

    /**
     * 用于前端下载文件时，返回下载文件的文件流
     *
     * @param response
     * @param filePath
     * @param fileName
     *
     * @author sunday
     */
    public void downloadFile( HttpServletResponse response,String filePath,String fileName)throws IOException{

        // 设置返回流到客户端
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        File file = new File(filePath+fileName);
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
            throw new RuntimeException("文件下载时，返回文件流出错！");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException("文件下载时，返回文件流出错！");
                }
            }
        }
    }

}
