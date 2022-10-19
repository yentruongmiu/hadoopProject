package averageComputing;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageComputingReducer extends
        Reducer<Text, LongWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text word, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Long sum = 0L;
        int count = 0;
        for (LongWritable value : values) {
            sum += value.get();
            count += 1;
        }
        Long avg = sum/count;
        context.write(word, new LongWritable(avg));
    }
}
