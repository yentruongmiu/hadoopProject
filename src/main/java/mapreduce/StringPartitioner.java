package mapreduce;

import org.apache.hadoop.mapreduce.Partitioner;

public class StringPartitioner extends Partitioner<String, Class> {
    @Override
    public int getPartition(String s, Class aClass, int numReduceTask) {
        if(numReduceTask == 1) return 0;
        return Math.abs(s.hashCode()) % numReduceTask;
    }
}
