package mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

import java.io.IOException;
import java.text.MessageFormat;

public class MapReduceJob<M extends Mapper<?, ?, ?, ?>,
	R extends Reducer<?, ?, ?, ?>, P extends Partitioner<?, ?>,
	K, V>
	extends Configured implements Tool {

	private final Job job;

	public MapReduceJob(String jobName, Class<M> mapper, Class<R> reducer, Class<P> partitioner,
						Class<K> outputKey, Class<V> outputValue) throws IOException {
		Configuration conf = new Configuration();
		job = Job.getInstance(conf, jobName);
		job.setJarByClass(this.getClass());

		job.setOutputKeyClass(outputKey);
		job.setOutputValueClass(outputValue);

		job.setMapperClass(mapper);
		job.setReducerClass(reducer);
		job.setPartitionerClass(partitioner);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
	}

	@Override
	public int run(String[] strings) throws Exception {
		//strings: 0: jobName, 1: inputPath, 2: outputPath, 3: numReducers
		String inputPath = MessageFormat.format("{0}/{1}", strings[1], job.getJobName());
		String outputPath = MessageFormat.format("{0}/{1}", strings[2], job.getJobName());

		Path input = new Path(inputPath);
		Path output = new Path(outputPath);
		FileInputFormat.addInputPath(job, input);
		FileInputFormat.setInputDirRecursive(job, true);
		FileOutputFormat.setOutputPath(job, output);

		//set default numReducers is 1;
		Integer numReducers;
		if (strings.length == 4 && Integer.parseInt(strings[3]) > 1) {
			numReducers = Integer.parseInt(strings[3]);
		} else {
			numReducers = 1;
		}
		job.setNumReduceTasks(numReducers);

		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);

		//delete output directory if exit
		if (fs.exists(output)) {
			fs.delete(output, true);
		}

		return job.waitForCompletion(true) ? 0 : 1;
	}
}
