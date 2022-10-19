package frequenciesPairsComputing;

import mapReduce.MapReduceJob;
import mapReduce.StringIntPartitioner;
import org.apache.hadoop.io.IntWritable;

import java.io.IOException;

public class FrequenciesPairsComputingJob extends MapReduceJob<FrequenciesPairsComputingMapper,
	FrequenciesPairsComputingReducer, StringIntPartitioner,
	Pair, IntWritable> {

	public FrequenciesPairsComputingJob(String jobName) throws IOException {
		super(jobName, FrequenciesPairsComputingMapper.class,
			FrequenciesPairsComputingReducer.class, StringIntPartitioner.class,
			Pair.class, IntWritable.class);
	}
}
