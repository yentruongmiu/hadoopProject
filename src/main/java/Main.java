import mapreduce.MapReduceJob;
import org.apache.hadoop.util.ToolRunner;
import wordcount.WordCountJob;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("main");

        MapReduceJob<?, ?, ?, ?> job = null;
        //arguments: <p1a,p1b,p1c,p1d,p2,p3,p4> <inputPath> <outputPath> [<numReducers>]
        String jobName = args[0];
        switch (jobName) {
            case "p1a":
                job = new WordCountJob("WordCountJob");
                break;

            case "p1b":
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

        int result = ToolRunner.run(job, args);

        System.exit(result);
    }
}
