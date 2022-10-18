package wordcount;

import mapreduce.MapReduceJob;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class WordCountJob extends MapReduceJob<WordCountMapper, WordCountReducer, Text, IntWritable> {
    public WordCountJob(String jobName) throws IOException {
        super(jobName, WordCountMapper.class, WordCountReducer.class, Text.class, IntWritable.class);
    }
}
