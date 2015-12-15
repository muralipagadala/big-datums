package com.bigdatums.hashing;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BdHashingUtils {

    public static String md5OfString(String str){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return byteArrayToHex(digest);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch(NullPointerException e){
            System.out.println("NullPointerException: Please provide a valid string");
        }

        return null;
    }

    public static String md5OfFile(File file){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fs = new FileInputStream(file);
            BufferedInputStream bs = new BufferedInputStream(fs);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while((bytesRead = bs.read(buffer, 0, buffer.length)) != -1){
                md.update(buffer, 0, bytesRead);
            }

            byte[] digest = md.digest();
            return byteArrayToHex(digest);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch(FileNotFoundException e){
            System.out.println(e);
        } catch(IOException e){
            System.out.println(e);
        }

        return null;
    }

    public static String byteArrayToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte bite : bytes){
            sb.append(String.format("%02x", bite & 0xff));
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(BdHashingUtils.md5OfString("this is a test string"));
        System.out.println(BdHashingUtils.md5OfString(null));
    }

}
