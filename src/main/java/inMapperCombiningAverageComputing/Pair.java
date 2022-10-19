package inMapperCombiningAverageComputing;

public class Pair {
    private Double key;
    private Integer value;

    public Pair(Double key, Integer value) {
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
}
