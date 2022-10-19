package averageComputing;

import mapReduce.MapReduceJob;
import mapReduce.StringDoublePartitioner;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AverageComputingJob extends
        MapReduceJob<AverageComputingMapper, AverageComputingReducer, StringDoublePartitioner, Text, DoubleWritable> {

    public AverageComputingJob(String jobName) throws IOException {
        super(jobName, AverageComputingMapper.class,
                AverageComputingReducer.class,
                StringDoublePartitioner.class, Text.class, DoubleWritable.class);
    }
}
