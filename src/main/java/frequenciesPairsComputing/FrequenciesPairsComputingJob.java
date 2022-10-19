package frequenciesPairsComputing;

import mapReduce.MapReduceJob;
import mapReduce.StringPartitioner;
import org.apache.hadoop.io.IntWritable;

import java.io.IOException;

public class FrequenciesPairsComputingJob extends MapReduceJob<FrequenciesPairsComputingMapper,
	FrequenciesPairsComputingReducer, StringPartitioner,
	Pair, IntWritable> {

	public FrequenciesPairsComputingJob(String jobName) throws IOException {
		super(jobName, FrequenciesPairsComputingMapper.class,
			FrequenciesPairsComputingReducer.class, StringPartitioner.class,
			Pair.class, IntWritable.class);
	}
}
