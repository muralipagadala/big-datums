package com.bigdatums.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.IOException;


public class UsingCustomWritableExample1 extends Configured implements Tool {

    public static class Map extends Mapper<Object, Text, Text, GenderLoginWritable> {
        public Text areaCode = new Text();
        public IntWritable male = new IntWritable();
        public IntWritable female = new IntWritable();
        public IntWritable maleLogins = new IntWritable();
        public IntWritable femaleLogins = new IntWritable();

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\t");

            try {
                areaCode.set(fields[3].substring(0, 3));

                if (fields[7].equals("MALE")) {
                    male.set(1);
                    maleLogins.set(Integer.parseInt(fields[10]));
                    female.set(0);
                    femaleLogins.set(0);
                } else {
                    male.set(0);
                    maleLogins.set(0);
                    female.set(1);
                    femaleLogins.set(Integer.parseInt(fields[10]));
                }
            }
            catch(Exception e){return;}

            context.write(areaCode, new GenderLoginWritable(male, maleLogins, female, femaleLogins));
        }
    }

    public static class Reduce extends Reducer<Text, GenderLoginWritable, Text, GenderLoginWritable> {

        @Override
        public void reduce(Text key, Iterable<GenderLoginWritable> values, Context context) throws IOException, InterruptedException {

            int male = 0;
            int maleLogins = 0;
            int female = 0;
            int femaleLogins = 0;

            for(GenderLoginWritable value : values) {
                male = male + value.getMale().get();
                maleLogins = maleLogins + value.getMaleLogins().get();
                female = female + value.getFemale().get();
                femaleLogins = femaleLogins + value.getFemaleLogins().get();
            }

            context.write(key, new GenderLoginWritable(new IntWritable(male), new IntWritable(maleLogins),
                    new IntWritable(female), new IntWritable(femaleLogins)));
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();

        //input and output paths passed from cli
        Path hdfsInputPath = new Path(args[0]);
        Path hdfsOutputPath = new Path(args[1]);

        //create job
        Job job = new Job(conf, "Using a Custom Writable Example 1");
        job.setJarByClass(UsingCustomWritableExample1.class);

        //set mapper and reducer
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        //set key and value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(GenderLoginWritable.class);

        //set input format and path
        FileInputFormat.addInputPath(job, hdfsInputPath);
        job.setInputFormatClass(TextInputFormat.class);

        //set output format and path
        FileOutputFormat.setOutputPath(job, hdfsOutputPath);
        job.setOutputFormatClass(TextOutputFormat.class);

        //run job return status
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new UsingCustomWritableExample1(), args);
        System.exit(res);
    }
}
