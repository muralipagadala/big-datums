package com.bigdatums.datagen;

import org.junit.Test;
import org.junit.Assert;
import org.jfairy.Fairy;

public class BdPersonTest {

    private int size = 1000;
    private String[][] data = new String[1000][12];

    public BdPersonTest(){
        BdPerson p = new BdPerson();
        Fairy fairy = Fairy.create();
        for(int i=0; i<size; i++){
            p.populateRandom(fairy, false);
            data[i] = p.toArray();
        }
    }

    @Test
    public void testPeopleData(){
        //test every cell has value
        boolean flag = true;

        flag_loop:
        for(int i=0; i<data.length; i++){
            String[] di = data[i];
            for(int j=0; j<di.length; j++){
                //middle names (index 6) can be empty
                if(di[j].length() == 0 && j != 6) {
                    flag = false;
                    break flag_loop;
                }
            }
        }
        Assert.assertEquals(flag, true);
    }

}
