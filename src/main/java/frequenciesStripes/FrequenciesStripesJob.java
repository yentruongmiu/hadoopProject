package frequenciesStripes;

import mapReduce.MapReduceJob;
import mapReduce.StringDoublePartitioner;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class FrequenciesStripesJob extends
        MapReduceJob<FrequenciesStripesMapper,
                FrequenciesStripesReducer,
                StringDoublePartitioner, Text, Stripe> {

    public FrequenciesStripesJob(String jobName) throws IOException {
        super(jobName, FrequenciesStripesMapper.class,
                FrequenciesStripesReducer.class,
                StringDoublePartitioner.class,
                Text.class, Stripe.class);
    }
}
