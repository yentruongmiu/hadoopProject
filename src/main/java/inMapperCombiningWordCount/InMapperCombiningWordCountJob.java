package inMapperCombiningWordCount;

import mapReduce.MapReduceJob;
import mapReduce.StringIntPartitioner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class InMapperCombiningWordCountJob extends MapReduceJob<InMapperCombiningWordCountMapper,
        InMapperCombiningWordCountReducer, StringIntPartitioner,
        Text, IntWritable> {

    public InMapperCombiningWordCountJob(String jobName) throws IOException {
        super(jobName, InMapperCombiningWordCountMapper.class,
                InMapperCombiningWordCountReducer.class, StringIntPartitioner.class,
                Text.class, IntWritable.class);
    }
}
