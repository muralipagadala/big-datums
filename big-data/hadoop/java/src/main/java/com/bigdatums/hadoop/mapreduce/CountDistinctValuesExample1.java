package com.bigdatums.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.NullWritable;
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
import java.util.HashSet;


public class CountDistinctValuesExample1 extends Configured implements Tool {

    public static class Map extends Mapper<LongWritable, Text, NullWritable, Text> {
        private Text word = new Text();

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");
            String firstName = fields[1];
            word.set(firstName);
            context.write(NullWritable.get(), word);
        }
    }

    public static class Reduce extends Reducer<NullWritable, Text, IntWritable, NullWritable> {
        @Override
        public void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            HashSet<Text> words = new HashSet<Text>();
            for(Text value : values) {
                words.add(value);
            }
            context.write(new IntWritable(words.size()), NullWritable.get());
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();

        //input and output paths passed from cli
        Path hdfsInputPath = new Path(args[0]);
        Path hdfsOutputPath = new Path(args[1]);

        //create job
        Job job = new Job(conf, "Count Distinct Values Example 1");
        job.setJarByClass(CountDistinctValuesExample1.class);

        //set mapper and reducer
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        //set key and value classes
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(NullWritable.class);

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
        int res = ToolRunner.run(new Configuration(), new CountDistinctValuesExample1(), args);
        System.exit(res);
    }
}

