package com.bigdatums.hashing;

import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.io.PrintWriter;

public class BdHashingUtilsTest {

    @Test
    public void testMD5OfString(){
        Assert.assertEquals(BdHashingUtils.md5OfString("my test string").toUpperCase(), "751F27E6459B10CE917994A81E76212D");
        Assert.assertEquals(BdHashingUtils.md5OfString(null), null);
    }

    @Test
    public void testMD5OfFile(){

        try {
            File file = new File("tmp.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println("The first line");
            writer.println("The second line");
            writer.close();

            Assert.assertEquals(BdHashingUtils.md5OfFile(file).toUpperCase(), "05759F1089C91E3524E2AEBA213EC60D");

            file.delete();
        }catch(Exception e){
            System.out.println(e);
        }


    }





}
