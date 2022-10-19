package averageComputing;

import mapReduce.MapReduceJob;
import mapReduce.StringPartitioner;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AverageComputingJob extends
        MapReduceJob<AverageComputingMapper, AverageComputingReducer, StringPartitioner, Text, LongWritable> {

    public AverageComputingJob(String jobName) throws IOException {
        super(jobName, AverageComputingMapper.class,
                AverageComputingReducer.class,
                StringPartitioner.class, Text.class, LongWritable.class);
    }
}
