package com.bigdatums.compression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;

public class GZip {

    public static final int bufferSize = 1024;

    public void GZipFile(String inputPath, String outputPath) throws Exception {

        FileInputStream fis = new FileInputStream(inputPath);
        FileOutputStream fos = new FileOutputStream(outputPath);
        GZIPOutputStream gos = new GZIPOutputStream(fos);
        byte[] buffer = new byte[bufferSize];

        try{
            int len;
            while((len = fis.read(buffer)) != -1) {
                 gos.write(buffer, 0, len);
            }
        } finally {
            try{if(fis != null) fis.close();} catch(Exception e){}
            try{if(gos != null) gos.close();} catch(Exception e){}
        }
    }

    public static void main(String[] args) throws Exception {
        GZip gz = new GZip();
        gz.GZipFile("sample_data_1.txt", "sample_data_1.txt.gz");
    }

}
