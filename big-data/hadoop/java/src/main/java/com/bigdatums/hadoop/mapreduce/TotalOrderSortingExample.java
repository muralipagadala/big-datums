package com.bigdatums.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;


public class TotalOrderSortingExample extends Configured implements Tool {

    public static class ExtractSortedValueMap extends Mapper<Object, Text, Text, NullWritable> {
        Text word = new Text();
        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\t");
            word.set(fields[1]);
            context.write(word, NullWritable.get());
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();

        //input and output paths passed from cli
        Path inputPath = new Path(args[0]);
        Path stageOutputPath = new Path(args[1]);
        Path partitionerFile = new Path(args[2]);
        Path sortedOutputPath = new Path(args[3]);

        //create job to extract values to be sorted
        Job samplingJob = new Job(conf, "Total Order Sorting Example - Extract Job");
        samplingJob.setJarByClass(TotalOrderSortingExample.class);

        //set mapper to extract value
        //first job has no reducer
        samplingJob.setMapperClass(ExtractSortedValueMap.class);
        samplingJob.setNumReduceTasks(0);

        //set key and value classes
        samplingJob.setOutputKeyClass(Text.class);
        samplingJob.setOutputValueClass(NullWritable.class);

        //set input format and path
        FileInputFormat.addInputPath(samplingJob, inputPath);
        samplingJob.setInputFormatClass(TextInputFormat.class);

        //set output format and path
        FileOutputFormat.setOutputPath(samplingJob, stageOutputPath);
        samplingJob.setOutputFormatClass(SequenceFileOutputFormat.class);

        //run job return status
        int code = samplingJob.waitForCompletion(true) ? 0 : 1;

        //run total ordering job if first job successful
        if(code == 0) {
            //create total ordering job
            Job sortingJob = new Job(conf, "Total Order Sorting Example - Partition and Sort Job");
            sortingJob.setJarByClass(TotalOrderSortingExample.class);

            //input and output formats
            sortingJob.setInputFormatClass(SequenceFileInputFormat.class);
            SequenceFileInputFormat.setInputPaths(sortingJob, stageOutputPath);
            TextOutputFormat.setOutputPath(sortingJob, sortedOutputPath);

            //mapper settings
            sortingJob.setMapperClass(Mapper.class);
            sortingJob.setMapOutputKeyClass(Text.class);
            sortingJob.setMapOutputValueClass(NullWritable.class);

            //reducer settings
            sortingJob.setNumReduceTasks(5);
            sortingJob.setReducerClass(Reducer.class);
            sortingJob.setOutputFormatClass(TextOutputFormat.class);

            //create total order partitioner file
            TotalOrderPartitioner.setPartitionFile(sortingJob.getConfiguration(), partitionerFile);
            InputSampler.Sampler<Text, NullWritable> inputSampler = new InputSampler.RandomSampler<Text, NullWritable>(.01, 1000, 100);
            InputSampler.writePartitionFile(sortingJob, inputSampler);
            sortingJob.setPartitionerClass(TotalOrderPartitioner.class);

            //run total ordering job
            code = sortingJob.waitForCompletion(true) ? 0 : 2;
        }

        //deleting first mapper output and partitioner file
        FileSystem.get(new Configuration()).delete(partitionerFile, false);
        FileSystem.get(new Configuration()).delete(stageOutputPath, true);

        return code;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new TotalOrderSortingExample(), args);
        System.exit(res);
    }
}