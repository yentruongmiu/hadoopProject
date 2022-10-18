package inmappercombiningwordcount;

import mapreduce.MapReduceJob;
import mapreduce.StringPartitioner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class InMapperCombiningWordCountJob extends MapReduceJob<InMapperCombiningWordCountMapper,
        InMapperCombiningWordCountReducer, StringPartitioner,
        Text, IntWritable> {

    public InMapperCombiningWordCountJob(String jobName) throws IOException {
        super(jobName, InMapperCombiningWordCountMapper.class,
                InMapperCombiningWordCountReducer.class, StringPartitioner.class,
                Text.class, IntWritable.class);
    }
}
