package inMapperCombiningAverageComputing;

import accessLog.Parser;
import accessLog.Record;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMapperCombiningAverageComputingMapper
        extends Mapper<LongWritable, Text, Text, Pair> {

    private Map<Text, Pair> groupPairs;
    private Logger logger = Logger.getLogger(InMapperCombiningAverageComputingMapper.class);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException{
        //do as initialize() method
        groupPairs = new HashMap<>();
    }

    @Override
    protected void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
        Record r = Parser.parserRecord(record);
        if(r != null) {
            Text text = new Text(r.getIpAddress());
            Pair pair;
            if(groupPairs.containsKey(text)) {
                pair = groupPairs.get(text);
                Double nKey = pair.getKey() + (double) r.getQuantity();
                Integer nValue = pair.getValue() + 1;
                pair.setValue(nValue);
                pair.setKey(nKey);
            } else {
                pair = new Pair((double) r.getQuantity(), 1);
            }
            groupPairs.put(text, pair);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //do as close() method
        for(Text key : groupPairs.keySet()) {
            Pair pair = groupPairs.get(key);
            context.write(key, pair);
        }
    }
}
