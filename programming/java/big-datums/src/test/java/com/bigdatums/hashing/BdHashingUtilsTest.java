package com.bigdatums.hashing;

import org.junit.Test;
import org.junit.Assert;

public class BdHashingUtilsTest {

    @Test
    public void testMD5OfString(){
        Assert.assertEquals(BdHashingUtils.md5OfString("my test string").toUpperCase(), "751F27E6459B10CE917994A81E76212D");
        Assert.assertEquals(BdHashingUtils.md5OfString(null), null);
    }


}
