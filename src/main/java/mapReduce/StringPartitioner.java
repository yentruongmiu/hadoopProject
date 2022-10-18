package mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StringPartitioner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text s, IntWritable value, int numReduceTask) {
        if(numReduceTask == 1) return 0;
        return Math.abs(s.toString().hashCode()) % numReduceTask;
    }
}
