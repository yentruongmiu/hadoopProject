package averageComputing;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StringDoublePartitioner extends Partitioner<Text, DoubleWritable> {
    @Override
    public int getPartition(Text text, DoubleWritable value, int numReduceTask) {
        if(numReduceTask == 1) return 0;
        return Math.abs(text.toString().hashCode()) % numReduceTask;
    }
}
