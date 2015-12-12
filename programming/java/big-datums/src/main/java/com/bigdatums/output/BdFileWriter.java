package com.bigdatums.output;

import com.bigdatums.datagen.BdDataGen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BdFileWriter {

    public static<T> void listToFile(List<T> listInput, String outputFilePath) {
        try {
            FileWriter fw = new FileWriter(new File(outputFilePath));
            BufferedWriter bw = new BufferedWriter(fw);
            for(T line : listInput) {
                bw.write(line.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void main(String[] args){
        BdDataGen bdDataGen = new BdDataGen();
        List<String> output = bdDataGen.genPeopleData(10, true, false, "\t");
        BdFileWriter.listToFile(output, "/home/nik/Downloads/BufferedWriterTest.txt");
    }

}
