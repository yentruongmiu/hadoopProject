package inMapperCombiningAverageComputing;

import mapReduce.MapReduceJob;
import mapReduce.StringDoublePartitioner;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;

import java.io.IOException;

public class InMapperCombiningAverageComputingJob extends
        MapReduceJob<InMapperCombiningAverageComputingMapper, InMapperCombiningAverageComputingReducer, StringDoublePartitioner, Text, Pair> {

    private Logger logger = Logger.getLogger(InMapperCombiningAverageComputingJob.class);
    public InMapperCombiningAverageComputingJob(String jobName) throws IOException {
        super(jobName, InMapperCombiningAverageComputingMapper.class,
                InMapperCombiningAverageComputingReducer.class,
                StringDoublePartitioner.class, Text.class,
                Pair.class);
        logger.info("InMapperCombiningAverageComputingJob class");
    }
}
