import inmappercombiningwordcount.InMapperCombiningWordCountJob;
import mapreduce.MapReduceJob;
import org.apache.hadoop.util.ToolRunner;
import wordcount.WordCountJob;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 3) {
            System.out.println("Enter command such as: bin/hadoop jar hadoopProject.jar Main <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]");
            return;
        }

        MapReduceJob<?, ?, ?, ?, ?> job = null;
        //arguments: <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]
        String jobName = args[0];
        switch (jobName) {
            case "p1a":
                job = new WordCountJob("WordCountJob");
                break;

            case "p1b":
                job = new InMapperCombiningWordCountJob("InMapperCombiningWordCountJob");
                break;

            case "p1c":
                break;

            case "p1d":
                break;

            case "p2":
                break;

            case "p3":
                break;

            case "p4":
                break;

            default:
                break;
        }

        int exitCode = ToolRunner.run(job, args);

        System.exit(exitCode);
    }
}
