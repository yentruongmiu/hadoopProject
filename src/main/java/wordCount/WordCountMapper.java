package wordCount;

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
    protected void map(LongWritable key, Text record, Context context)
            throws IOException, InterruptedException {

        String[] words = record.toString().split("[ \\-\"\']");
        Pattern pattern = Pattern.compile("^[A-Za-z]+[!,?.]*$", Pattern.CASE_INSENSITIVE);

        for(String word : words) {
            word = word.trim();
            Matcher matcher = pattern.matcher(word);
            if(matcher.find()) {
                word = matcher.group().toLowerCase(Locale.ROOT);
                String endingChar = String.valueOf(word.charAt(word.length() - 1));

                if(endingChar.equals(".") || endingChar.equals("!")
                        || endingChar.equals(",") || endingChar.equals("?")) {
                    word = word.substring(0, word.length() - 1);
                }
                Text text = new Text(word);
                context.write(text, one);
            }
            // not matcher.find() => empty string or other cases => not care
        }
    }
}
