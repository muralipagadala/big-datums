package com.bigdatums.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BdHashingUtils {

    public static String md5OfString(String str){

        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            for(byte dByte : digest){
                sb.append(String.format("%02x", dByte & 0xff));
            }
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch(NullPointerException e){
            System.out.println("NullPointerException: Please provide a valid string");
        }

        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(BdHashingUtils.md5OfString("this is a test string"));
        System.out.println(BdHashingUtils.md5OfString(null));
    }

}
