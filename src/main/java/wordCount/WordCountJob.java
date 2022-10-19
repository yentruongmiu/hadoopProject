package wordCount;

import mapReduce.MapReduceJob;
import mapReduce.StringIntPartitioner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class WordCountJob
        extends MapReduceJob<WordCountMapper, WordCountReducer, StringIntPartitioner, Text, IntWritable> {
    public WordCountJob(String jobName) throws IOException {
        super(jobName, WordCountMapper.class,
                WordCountReducer.class, StringIntPartitioner.class,
                Text.class, IntWritable.class);
    }
}
