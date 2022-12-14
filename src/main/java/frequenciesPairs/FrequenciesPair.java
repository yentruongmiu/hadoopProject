package frequenciesPairs;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

public class FrequenciesPair implements Writable, WritableComparable<FrequenciesPair> {
	private String left;
	private String right;

	/**
	 * Required. DO NOT DELETE
	 */
	public FrequenciesPair() {
		super();
	}

	public FrequenciesPair(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}

	public String getLeft() {
		return this.left;
	}

	public boolean isSpecialToken() {
		return isSpecialToken(this.right);
	}

	public boolean isSpecialToken(String right) {
		return right.equals("*");
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		left = in.readUTF();
		right = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(left);
		out.writeUTF(right);
	}

	@Override
	public int compareTo(FrequenciesPair o) {
		int k = this.left.compareTo(o.left);
		return k == 0 ? this.right.compareTo(o.right) : k;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		FrequenciesPair other = (FrequenciesPair) obj;

		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;

		return Objects.equals(right, other.right);
	}

	@Override
	public String toString() {
		return MessageFormat.format("({0}, {1})", left, right);
	}
}
