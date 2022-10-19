import averageComputing.AverageComputingJob;
import inMapperCombiningAverageComputing.InMapperCombiningAverageComputingJob;
import inMapperCombiningWordCount.InMapperCombiningWordCountJob;
import mapReduce.MapReduceJob;
import org.apache.hadoop.util.ToolRunner;
import frequenciesPairsComputing.FrequenciesPairsComputingJob;
import wordCount.WordCountJob;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Enter command such as: bin/hadoop jar hadoopProject.jar Main <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]");
			return;
		}

		MapReduceJob<?, ?, ?, ?, ?> job = null;
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
				job = new FrequenciesPairsComputingJob("P2_RelativeFrequenciesPairsAlgoComputing");
				break;

			case "p3":
				break;

			case "p4":
				break;

			default:
				break;
		}

		if (job == null) {
			System.out.println("Missing target. Enter command such as: bin/hadoop jar hadoopProject.jar Main <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]");
			return;
		}

		int exitCode = ToolRunner.run(job, args);

		System.exit(exitCode);
	}
}
