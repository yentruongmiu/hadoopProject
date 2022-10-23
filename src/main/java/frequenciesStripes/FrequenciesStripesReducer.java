package frequenciesStripes;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Set;


public class FrequenciesStripesReducer extends Reducer<Text, Stripe, Text, Stripe> {
    private Logger logger = Logger.getLogger(FrequenciesStripesReducer.class);
    @Override
    protected void reduce(Text u, Iterable<Stripe> values, Context context) throws IOException, InterruptedException {

        Stripe Hf = new Stripe();
        Integer sum = 0;
        for(Stripe H : values) {
            Set<Writable> keys = H.keySet();
            for(Writable key : keys) {
                IntWritable kValue = (IntWritable) H.get(key);
                if(Hf.containsKey(key)) {
                    IntWritable value = (IntWritable) Hf.get(key);
                    value.set(kValue.get() + value.get());
                    Hf.put(key, value);
                } else {
                    Hf.put(key, kValue);
                }
                sum += kValue.get();
            }
        }
        Set<Writable> keys = Hf.keySet();
        for(Writable key : keys) {
            IntWritable value = (IntWritable) Hf.get(key);
            Double frequency = (double)((double)value.get() / sum);
            Hf.put(key, new DoubleWritable(frequency));
        }

        context.write(u, Hf);
    }
}
