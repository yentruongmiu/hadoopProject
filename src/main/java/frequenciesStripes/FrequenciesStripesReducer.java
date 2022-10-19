package frequenciesStripes;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Set;


public class FrequenciesStripesReducer extends
        Reducer<Text, Stripe, Text, Stripe> {

    @Override
    protected void reduce(Text u, Iterable<Stripe> values, Context context)
            throws IOException, InterruptedException {
        Stripe Hf = new Stripe();
        int sum = 0;
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
            Hf.put(key, new DoubleWritable(value.get() / sum));
        }

        context.write(u, Hf);

        //for all stripe H in [H1,H2,...] do
            //Hf = Hf + H
        //s = Sum (Hf)
        //Emit(u, Hf/s)
    }
}
