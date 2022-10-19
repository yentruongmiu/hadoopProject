package inMapperCombiningAverageComputing;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StringPairPartitioner extends Partitioner<Text, Pair> {

    @Override
    public int getPartition(Text text, Pair pair, int numReduceTask) {
        if(numReduceTask == 1) return 0;
        return Math.abs(text.toString().hashCode()) % numReduceTask;
    }
}
