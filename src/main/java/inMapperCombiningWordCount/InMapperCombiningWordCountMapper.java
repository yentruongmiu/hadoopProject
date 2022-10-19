package inMapperCombiningWordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InMapperCombiningWordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Map<Text, IntWritable> groupPairs;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException{
        //do as initialize() method
        groupPairs = new HashMap<>();
    }

    @Override
    protected void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
        String[] words = record.toString().split("[ \\-\"\']");
        Pattern pattern = Pattern.compile("^[A-Za-z]+[!,?.]*$", Pattern.CASE_INSENSITIVE);

        if(words.length > 0) {
            for (String word : words) {
                word = word.trim();
                Matcher matcher = pattern.matcher(word);
                if (matcher.find()) {
                    word = matcher.group().toLowerCase(Locale.ROOT);
                    String endingChar = String.valueOf(word.charAt(word.length() - 1));

                    if (endingChar.equals(".") || endingChar.equals("!")
                            || endingChar.equals(",") || endingChar.equals("?")) {
                        word = word.substring(0, word.length() - 1);
                    }
                    Text text = new Text(word);
                    if (groupPairs.containsKey(text)) {
                        IntWritable value = groupPairs.get(text);
                        Integer intValue = value.get() + 1;
                        value.set(intValue);
                        groupPairs.put(text, value);
                    } else {
                        groupPairs.put(text, new IntWritable(1));
                    }
                }
                // not matcher.find() => empty string or other cases => not care
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //do as close() method
        for(Text key : groupPairs.keySet()) {
            context.write(key, groupPairs.get(key));
        }
    }
}
