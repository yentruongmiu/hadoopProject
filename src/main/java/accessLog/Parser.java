package accessLog;

import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Logger logger = Logger.getLogger(Parser.class);
    public static Record parserRecord(Text record) {
        String line = record.toString();
        Pattern pattern = Pattern.compile("^([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}) ([- ])+ \\[(.+?)\\] \"(.+?)\" (\\d{3}) (\\d+)$");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            for(int i = 0; i <= matcher.groupCount(); i++) {
                //System.out.printf("%d %s\n", i, matcher.group(i));
                logger.info("" + i + " " + matcher.group(i) + "\n");
            }

            Record r = new Record(matcher.group(1),
                    matcher.group(3),
                    matcher.group(4),
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)));
            return r;
        } else {
            return null;
        }
    }
}
