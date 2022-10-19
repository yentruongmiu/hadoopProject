package frequenciesStripes;

import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;

import java.util.Set;

public class Stripe extends MapWritable {
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        Set<Writable> keys = this.keySet();
        int cnt = 0;
        for(Writable key : keys) {
            Writable count = this.get(key);
            s.append("(" + key.toString() + "," + count.toString() + ")");
            if(cnt < keys.size() - 1) {
                s.append(",");
            }
            cnt ++;
        }
        s.append("}");
        return s.toString();
    }
}
