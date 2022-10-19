import models.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WordCountExample extends Configured implements Tool {

	public static class WordCountMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Pair, IntWritable> {
		private final IntWritable value1 = new IntWritable(1);
		private Map<Pair, IntWritable> groupPairs;

		@Override
		protected void setup(Context context) {
			groupPairs = new HashMap<>();
		}

		@Override
		protected void map(LongWritable key, Text record, Context context) {
			String[] terms = record.toString().trim().split(" ");

			for (int i = 0; i < terms.length; i++) {
				for (int j = i + 1; j < terms.length; j++) {
					String u = terms[i];
					String v = terms[j];

					if (u.equalsIgnoreCase(v)) {
						break;
					}

					Pair currentPair = new Pair(u, v);
					Pair smallestPair = new Pair(u, "*");

					groupPairs.put(currentPair, value1);
					groupPairs.put(smallestPair, value1);
				}
			}
		}

		@Override
		protected void cleanup(Context context) throws IOException, InterruptedException {
			for (Pair key : groupPairs.keySet()) {
				context.write(key, groupPairs.get(key));
			}
		}
	}

	public static class WordCountReducer extends Reducer<Pair, IntWritable, Pair, IntWritable> {
		@Override
		public void reduce(Pair key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = this.getConf();
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(args[1]), true);

		Job job = Job.getInstance(conf, "wordCount");
		job.setJarByClass(WordCountExample.class);

		job.setOutputKeyClass(Pair.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0] + "/WordCountExample"));
		FileInputFormat.setInputDirRecursive(job, true);
		FileOutputFormat.setOutputPath(job, new Path(args[1] + "/WordCountExample"));

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int returnCode = ToolRunner.run(new Configuration(), new WordCountExample(), args);
		System.exit(returnCode);
	}
}