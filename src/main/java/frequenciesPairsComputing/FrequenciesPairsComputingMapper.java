package frequenciesPairsComputing;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FrequenciesPairsComputingMapper extends Mapper<LongWritable, Text, Pair, IntWritable> {
	private final IntWritable value1 = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
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

				context.write(currentPair, value1);
				context.write(smallestPair, value1);
			}
		}
	}
}
