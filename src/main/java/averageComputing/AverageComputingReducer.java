package averageComputing;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageComputingReducer extends
        Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    @Override
    protected void reduce(Text word, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        Double sum = 0D;
        int count = 0;
        for (DoubleWritable value : values) {
            sum += value.get();
            count += 1;
        }
        Double avg = sum/count;
        context.write(word, new DoubleWritable(avg));
    }
}
