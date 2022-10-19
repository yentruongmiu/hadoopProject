package inMapperCombiningAverageComputing;

import mapReduce.MapReduceJob;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class InMapperCombiningAverageComputingJob extends
        MapReduceJob<InMapperCombiningAverageComputingMapper, InMapperCombiningAverageComputingReducer, StringPairPartitioner, Text, Pair> {

    public InMapperCombiningAverageComputingJob(String jobName) throws IOException {
        super(jobName, InMapperCombiningAverageComputingMapper.class,
                InMapperCombiningAverageComputingReducer.class,
                StringPairPartitioner.class, Text.class,
                Pair.class);
    }
}
