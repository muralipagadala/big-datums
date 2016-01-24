package com.bigdatums.time;

import org.junit.Test;
import org.junit.Assert;

public class BdTimeUtilsTest {

    @Test
    public void tsToEpoch8601Test(){
        //test tsToEpoch8601
        Assert.assertEquals((Integer) 1451606400, BdTimeUtils.tsToSec8601("2016-01-01T00:00:00.000-0000"));
        Assert.assertEquals((Integer) 1451635200, BdTimeUtils.tsToSec8601("2016-01-01T00:00:00.000-0800"));
        Assert.assertNull(BdTimeUtils.tsToSec8601("Test String"));
        Assert.assertNull(BdTimeUtils.tsToSec8601(""));
        Assert.assertNull(BdTimeUtils.tsToSec8601(null));
    }

}
