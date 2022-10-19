package frequenciesStripes;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StringStripePartitioner extends Partitioner<Text, Stripe> {
    @Override
    public int getPartition(Text text, Stripe stripe, int numReducers) {
        if(numReducers == 1) return 0;
        return Math.abs(text.toString().hashCode()) % numReducers;
    }
}
