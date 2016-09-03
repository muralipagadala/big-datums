package com.bigdatums.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MRUnitExample {

    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @Before
    public void setup() {
        //setup driver objects to be used in tests
        ToolMapReduceExample.Map  mapper = new ToolMapReduceExample.Map();
        ToolMapReduceExample.Reduce reducer = new ToolMapReduceExample.Reduce();
        mapDriver = new MapDriver(mapper);
        reduceDriver = new ReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() throws Exception {
        //create input
        Text tsvString = new Text("3999\tldowns\tdowns@gmail.com\t178-728-6484\tLayla\tDowns\t\tFEMALE\t1963-08-18\t2007-09-30T14:07:53.564-07:00\t8612\t210.22.82.167");

        //set input
        mapDriver.withInput(new LongWritable(), new Text(tsvString));

        //expected output
        mapDriver.withOutput(new Text("ldowns"), new IntWritable(1));

        //run test
        mapDriver.runTest();
    }

    @Test
    public void testReducer() throws Exception {
        //create input
        Text key = new Text("test key");
        IntWritable value = new IntWritable(1);

        //add values to list three times
        List<IntWritable> values = new ArrayList<IntWritable>();
        values.add(value);
        values.add(value);
        values.add(value);

        //set input
        reduceDriver.withInput(key, values);

        //expected output
        reduceDriver.withOutput(key, new IntWritable(3));

        //run test
        reduceDriver.runTest();
    }

    @Test
    public void testMapReduce() throws Exception {
        //create input
        Text input1 = new Text("3999\tldowns\tdowns@gmail.com\t178-728-6484\tLayla\tDowns\t\tFEMALE\t1963-08-18\t2007-09-30T14:07:53.564-07:00\t8612\t210.22.82.167");
        Text input2 = new Text("3999\tldowns\tdowns@gmail.com\t178-728-6484\tLayla\tDowns\t\tFEMALE\t1963-08-18\t2007-09-30T14:07:53.564-07:00\t8612\t210.22.82.167");
        Text input3 = new Text("4000\tjsampson\tsampson@yahoo.com\t292-071-9869\tJace\tSampsonNathan\tMALE\t2014-11-09\t2006-03-20T04:28:24.405-08:00\t370\t97.58.14.60");

        //set input
        mapReduceDriver.withInput(new LongWritable(), input1);
        mapReduceDriver.withInput(new LongWritable(), input2);
        mapReduceDriver.withInput(new LongWritable(), input3);

        //expected output
        mapReduceDriver.withOutput(new Text("jsampson"), new IntWritable(1));
        mapReduceDriver.withOutput(new Text("ldowns"), new IntWritable(2));

        //run test
        mapReduceDriver.runTest();
    }

    @Test
    public void testMapReduceOutputSize() throws Exception {
        //create input
        Text input1 = new Text("3999\tldowns\tdowns@gmail.com\t178-728-6484\tLayla\tDowns\t\tFEMALE\t1963-08-18\t2007-09-30T14:07:53.564-07:00\t8612\t210.22.82.167");
        Text input2 = new Text("3999\tldowns\tdowns@gmail.com\t178-728-6484\tLayla\tDowns\t\tFEMALE\t1963-08-18\t2007-09-30T14:07:53.564-07:00\t8612\t210.22.82.167");
        Text input3 = new Text("4000\tjsampson\tsampson@yahoo.com\t292-071-9869\tJace\tSampsonNathan\tMALE\t2014-11-09\t2006-03-20T04:28:24.405-08:00\t370\t97.58.14.60");

        //set input
        mapReduceDriver.withInput(new LongWritable(), input1);
        mapReduceDriver.withInput(new LongWritable(), input2);
        mapReduceDriver.withInput(new LongWritable(), input3);

        //expected output
        mapReduceDriver.withOutput(new Text("jsampson"), new IntWritable(1));
        mapReduceDriver.withOutput(new Text("ldowns"), new IntWritable(2));

        //get output from mapreduce
        List<Pair<Text, IntWritable>> output = mapReduceDriver.run();

        //test number of output records
        Assert.assertEquals(2, output.size());
    }
}
