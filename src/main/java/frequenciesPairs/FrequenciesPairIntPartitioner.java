package frequenciesPairs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class FrequenciesPairIntPartitioner extends Partitioner<FrequenciesPair, IntWritable> {
    @Override
    public int getPartition(FrequenciesPair s, IntWritable value, int numReduceTask) {
        if(numReduceTask == 1) return 0;
        return Math.abs(s.getLeft().hashCode()) % numReduceTask;
    }
}
