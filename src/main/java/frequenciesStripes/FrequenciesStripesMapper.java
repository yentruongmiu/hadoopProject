package frequenciesStripes;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;

public class FrequenciesStripesMapper extends
        Mapper<LongWritable, Text, Text, Stripe> {
    private Logger logger = Logger.getLogger(FrequenciesStripesMapper.class);
    @Override
    protected void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
        String[] words = record.toString().split("\\s+");
        if(words.length > 0) {
            for (int i = 0; i < words.length; i++) {
                Stripe H = new Stripe();

                for(int j = i + 1; j < words.length && !words[j].equals(words[i]); j++) {
                    Text vKey = new Text(words[j]);
                    if(H.containsKey(vKey)) {
                        IntWritable vValue = (IntWritable) H.get(vKey);
                        Integer curValue = vValue.get() + 1;
                        vValue.set(curValue);
                        H.put(vKey, vValue);
                    } else {
                        H.put(vKey, new IntWritable(1));
                    }
                }

                logger.info("==============Before Emit uKey and H==========");
                Text uKey = new Text(words[i]);
                logger.info("value of uKey " + words[i] + ":" + H.values().toString());
                context.write(uKey, H);
            }
        }
    }
}
