package frequenciesPairs;

import mapReduce.MapReduceJob;
import org.apache.hadoop.io.IntWritable;

import java.io.IOException;

public class FrequenciesPairsJob extends MapReduceJob<FrequenciesPairsMapper, FrequenciesPairsReducer, FrequenciesPairIntPartitioner,
	FrequenciesPair, IntWritable> {

	public FrequenciesPairsJob(String jobName) throws IOException {
		super(jobName, FrequenciesPairsMapper.class,
			FrequenciesPairsReducer.class, FrequenciesPairIntPartitioner.class,
			FrequenciesPair.class, IntWritable.class);
	}
}
