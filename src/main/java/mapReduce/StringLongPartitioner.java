package mapReduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StringLongPartitioner extends Partitioner<Text, LongWritable> {
    @Override
    public int getPartition(Text text, LongWritable value, int numReduceTask) {
        if(numReduceTask == 1) return 0;
        return Math.abs(text.toString().hashCode()) % numReduceTask;
    }
}
