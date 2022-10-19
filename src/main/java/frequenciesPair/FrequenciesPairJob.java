package frequenciesPair;

import mapReduce.MapReduceJob;
import mapReduce.StringIntPartitioner;
import org.apache.hadoop.io.IntWritable;

import java.io.IOException;

public class FrequenciesPairJob extends MapReduceJob<FrequenciesPairMapper,
	FrequenciesPairReducer, StringIntPartitioner,
	Pair, IntWritable> {

	public FrequenciesPairJob(String jobName) throws IOException {
		super(jobName, FrequenciesPairMapper.class,
			FrequenciesPairReducer.class, StringIntPartitioner.class,
			Pair.class, IntWritable.class);
	}
}
