package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String[] words = value.toString().split("[ \\-\"\']");
        Pattern pattern = Pattern.compile("^[A-Za-z]+[!,?.]*$", Pattern.CASE_INSENSITIVE);

        for(String text : words) {
            text = text.trim();
            Matcher matcher = pattern.matcher(text);
            if(matcher.find()) {
                text = matcher.group().toLowerCase(Locale.ROOT);
                String endingChar = String.valueOf(text.charAt(text.length() - 1));

                if(endingChar.equals(".") || endingChar.equals("!")
                        || endingChar.equals(",") || endingChar.equals("?")) {
                    text = text.substring(0, text.length() - 1);
                }
                Text word = new Text(text);
                context.write(word, one);
            }
            // not matcher.find() => empty string or other cases => not care
        }
    }
}
