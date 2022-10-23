package frequenciesPairs;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FrequenciesPairsReducer extends Reducer<FrequenciesPair, IntWritable, FrequenciesPair, DoubleWritable> {
	private int sum = 0;

	@Override
	protected void setup(Reducer<FrequenciesPair, IntWritable, FrequenciesPair, DoubleWritable>.Context context) {
		sum = 0;
	}

	@Override
	protected void reduce(FrequenciesPair key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int total = 0;
		for (IntWritable value : values) {
			total += value.get();
		}

		if (key.isSpecialToken()) {
			this.sum = total;
		} else {
			context.write(key, new DoubleWritable(total * 1.0 / this.sum));
		}
	}
}
