import averageComputing.AverageComputingJob;
import frequenciesPairs.FrequenciesPairsJob;
import frequenciesStripes.FrequenciesStripesJob;
import inMapperCombiningAverageComputing.InMapperCombiningAverageComputingJob;
import inMapperCombiningWordCount.InMapperCombiningWordCountJob;
import mapReduce.MapReduceJob;
import org.apache.hadoop.util.ToolRunner;
import wordCount.WordCountJob;

import java.text.MessageFormat;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Enter command such as: bin/hadoop jar hadoopProject.jar <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]");
			return;
		}

		MapReduceJob<?, ?, ?, ?, ?> job = null;
		boolean isP4Flow = false;
		//arguments: <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]
		String jobName = args[0];
		switch (jobName) {
			case "p1a":
				job = new WordCountJob("P1A_WordCount");
				break;

			case "p1b":
				job = new InMapperCombiningWordCountJob("P1B_InMapperCombiningWordCount");
				break;

			case "p1c":
				job = new AverageComputingJob("P1C_AverageComputing");
				break;

			case "p1d":
				job = new InMapperCombiningAverageComputingJob("P1D_InMapperCombiningAverageComputing");
				break;

			case "p2":
				job = new FrequenciesPairsJob("P2_FrequenciesPairs");
				break;

			case "p3":
				job = new FrequenciesStripesJob("P3_FrequenciesStripes");
				break;

			case "p4":
				isP4Flow = true;
				break;

			default:
				break;
		}

		if (isP4Flow) {
			p4Flow(args);
		} else {
			notP4Flow(job, args);
		}
	}

	private static void notP4Flow(MapReduceJob<?, ?, ?, ?, ?> job, String[] args) throws Exception {
		int exitCode = runTool(job, args);
		System.exit(exitCode);
	}

	private static int runTool(MapReduceJob<?, ?, ?, ?, ?> job, String[] args) throws Exception {
		if (job == null) {
			System.out.println("Missing target. Enter command such as: bin/hadoop jar hadoopProject.jar Main <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]");
			return -1;
		}

		return ToolRunner.run(job, args);
	}

	private static void p4Flow(String[] args) throws Exception {
		int exitCode = 0;

		String defaultArgs1 = args[1];
		String defaultArgs2 = args[2];

		for (int i = 1000; i <= 10000; i+= 1000) {
			System.out.println(MessageFormat.format("=== Run P4 at: {0} ===", String.valueOf(i)));
			args[1] = MessageFormat.format("{0}/{1}", defaultArgs1, String.valueOf(i));
			args[2] = MessageFormat.format("{0}/{1}", defaultArgs2, String.valueOf(i));
			exitCode = runTool(new FrequenciesPairsJob("P2_FrequenciesPairss"), args);

			if (exitCode != 0) {
				System.exit(exitCode);
			}
		}

		System.exit(exitCode);
	}
}