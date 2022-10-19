package frequenciesPairs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrequenciesPairsMapper extends Mapper<LongWritable, Text, FrequenciesPair, IntWritable> {
	private final IntWritable value1 = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
		String[] termArr = record.toString().trim().split(" ");
		List<String> terms = new ArrayList<>(Arrays.asList(termArr));
		terms.removeAll(Arrays.asList("", null));

		for (int i = 0; i < terms.size(); i++) {
			for (int j = i + 1; j < terms.size(); j++) {
				String u = terms.get(i);
				String v = terms.get(j);

				if (u.equalsIgnoreCase(v)) {
					break;
				}

				FrequenciesPair currentFrequenciesPair = new FrequenciesPair(u, v);
				FrequenciesPair smallestFrequenciesPair = new FrequenciesPair(u, "*");

				context.write(currentFrequenciesPair, value1);
				context.write(smallestFrequenciesPair, value1);
			}
		}
	}
}
