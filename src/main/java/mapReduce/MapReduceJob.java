package mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

public class MapReduceJob<M extends Mapper<?, ?, ?, ?>, R extends Reducer<?, ?, ?, ?>, P extends Partitioner<?, ?>, K, V>
	extends Configured implements Tool {
	private static final long MEGABYTE = 1024L * 1024L;
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
		String inputPath = strings[1];
		String outputPath = strings[2];

		Path input = new Path(inputPath);
		Path output = new Path(outputPath);
		FileInputFormat.addInputPath(job, input);
		FileInputFormat.setInputDirRecursive(job, true);
		FileOutputFormat.setOutputPath(job, output);

		//set default numReducers is 1;
		int numReducers = strings.length == 4 && Integer.parseInt(strings[3]) > 1 ? Integer.parseInt(strings[3]) : 1;
		job.setNumReduceTasks(numReducers);

		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);

		//delete output directory if exit
		if (fs.exists(output)) {
			fs.delete(output, true);
		}

		job.waitForCompletion(true);

		showResourcesUsage();

		return 0;
	}

	private void showResourcesUsage() throws IOException, InterruptedException {
		Counters counters = job.getCounters();
		long cpuMilliseconds = counters.findCounter(TaskCounter.CPU_MILLISECONDS).getValue();
		long physicalMemoryBytes = counters.findCounter(TaskCounter.PHYSICAL_MEMORY_BYTES).getValue();
		JobID jobId = job.getStatus().getJobID();

		System.out.println("=== JobId: " + jobId + " ===");
		System.out.println("CPU: " + (int) cpuMilliseconds / 1000 + " s");
		System.out.println("Memory: " + (int) physicalMemoryBytes / MEGABYTE + " MB");
		System.out.println("=====================================");
	}
}
