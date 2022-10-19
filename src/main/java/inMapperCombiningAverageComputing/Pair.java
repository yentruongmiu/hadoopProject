package inMapperCombiningAverageComputing;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pair implements Writable {
    private Double key;
    private Integer value;

    public Pair() {
        super();
    }

    public Pair(Double key, Integer value) {
        super();
        this.key = key;
        this.value = value;
    }
    public Double getKey() {
        return key;
    }

    public void setKey(Double key) {
        this.key = key;
    }

    public Integer getValue() { return value; }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "< " +
                key +
                ", " + value +
                " >";
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(key);
        out.writeInt(value);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        key = in.readDouble();
        value = in.readInt();
    }
}
