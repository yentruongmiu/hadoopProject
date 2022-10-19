package frequenciesStripes;

import mapReduce.MapReduceJob;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class FrequenciesStripesJob extends
        MapReduceJob<FrequenciesStripesMapper,
                FrequenciesStripesReducer,
                StringStripePartitioner, Text, Stripe> {

    public FrequenciesStripesJob(String jobName) throws IOException {
        super(jobName, FrequenciesStripesMapper.class,
                FrequenciesStripesReducer.class,
                StringStripePartitioner.class,
                Text.class, Stripe.class);
    }
}
