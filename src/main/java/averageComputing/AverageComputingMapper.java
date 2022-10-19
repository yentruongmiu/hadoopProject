package averageComputing;

import accessLog.Parser;
import accessLog.Record;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageComputingMapper extends
        Mapper<LongWritable, Text, Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
        Record r = Parser.parserRecord(record);
        if(r != null) {
            Text text = new Text(r.getIpAddress());
            DoubleWritable value = new DoubleWritable(r.getQuantity());
            context.write(text, value);
        }
    }
}
