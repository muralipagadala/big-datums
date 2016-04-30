package com.bigdatums.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.util.Iterator;


public class ToolMapReduceExample extends Configured implements Tool {

    public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(LongWritable key, Text value, Context context) {
            try {
                String line = value.toString();
                String[] fields = line.split("\t");
                String firstName = fields[1];
                word.set(firstName);
                context.write(word, one);
            }
             catch(Exception e) {}
        }
    }

    public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

        public void reduce(Text key, Iterator<IntWritable> values, Context context) {
            try {
                int sum = 0;
                while (values.hasNext()) {
                    sum += values.next().get();
                }
                context.write(key, new IntWritable(sum));
            }
            catch(Exception e) {}
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();

        //input and output paths passed from cli
        Path hdfsInputPath = new Path(args[0]);
        Path hdfsOutputPath = new Path(args[1]);

        //create job
        Job job = new Job(conf, "Tools Job Example");
        job.setJarByClass(ToolMapReduceExample.class);

        //set mapper and reducer
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        //set output key and value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

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
        int res = ToolRunner.run(new Configuration(), new ToolMapReduceExample(), args);
        System.exit(res);
    }
}
