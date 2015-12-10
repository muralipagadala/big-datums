package com.bigdatums.datagen;

import org.junit.Test;
import org.junit.Assert;

public class BdDataGenTest {

    @Test
    public void testPeopleData(){
        BdDataGen bd = new BdDataGen();
        String delimiter = "\t";
        String[] pd = bd.genPeopleData(100, true, false, delimiter);

        //test size
        Assert.assertEquals(100, pd.length);

        //test # of fields
        Assert.assertEquals(12, pd[0].split(delimiter).length);

        //test every cell has value
        boolean flag = true;

        flag_loop:
        for(int i=0; i<pd.length; i++){
            String[] pdi = pd[i].split(delimiter);

            for(int j=0; j<pdi.length; j++){
                //middle names (index 6) can be empty
                if(pdi[j].length() == 0 && j != 6) {
                    flag = false;
                    break flag_loop;
                }
            }
        }
        Assert.assertEquals(flag, true);
    }



    @Test
    public void encloseQuotesTest(){
        BdDataGen bd = new BdDataGen();
        String delimiter = "\t";

        String[] pd = bd.genPeopleData(10, true, true, delimiter);
        String pdStr = pd[0];

        //test first char is quote
        Assert.assertEquals('"', pdStr.charAt(0));

        //test last char is quote
        Assert.assertEquals('"', pdStr.charAt(pdStr.length()-1));

        //test total number of quotes + delim
        Assert.assertEquals(pdStr.split("\"" + delimiter + "\"").length, pdStr.split(delimiter).length);
    }

}
