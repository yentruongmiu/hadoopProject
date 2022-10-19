package inMapperCombiningAverageComputing;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class InMapperCombiningAverageComputingReducer  extends
        Reducer<Text, Pair, Text, DoubleWritable> {

    @Override
    protected void reduce(Text word, Iterable<Pair> values, Context context) throws IOException, InterruptedException {
        double sum = 0D;
        int count = 0;
        for (Pair value : values) {
            sum += value.getKey();
            count += value.getValue();
        }
        Double avg = sum/count;
        context.write(word, new DoubleWritable(avg));
    }
}
