package frequenciesPairsComputing;

import models.Pair;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrequenciesPairsComputingMapper extends Mapper<LongWritable, Text, Pair, IntWritable> {
	private final IntWritable value1 = new IntWritable(1);
	private Map<Pair, IntWritable> groupPairs;

	@Override
	protected void setup(Context context) {
		groupPairs = new HashMap<>();
	}

	@Override
	protected void map(LongWritable key, Text record, Context context) {
		String[] terms = record.toString().trim().split(" ");

		for (int i = 0; i < terms.length; i++) {
			for (int j = i + 1; j < terms.length; j++) {
				String u = terms[i];
				String v = terms[j];

				if (u.equalsIgnoreCase(v)) {
					break;
				}

				Pair currentPair = new Pair(u, v);
				Pair smallestPair = new Pair(u, "*");

				groupPairs.put(currentPair, value1);
				groupPairs.put(smallestPair, value1);
			}
		}
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		for (Pair key : groupPairs.keySet()) {
			context.write(key, groupPairs.get(key));
		}
	}
}
