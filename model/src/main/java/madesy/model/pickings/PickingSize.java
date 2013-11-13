package madesy.model.pickings;

import java.util.Random;

public class PickingSize {
	private static Random random = new Random();

	public static PickingSize generateRandomPickingSize() {
		return new PickingSize(random.nextInt(100) + 1,
				random.nextInt(100) + 1, random.nextInt(100) + 1);
	}

	private int width;
	private int height;
	private int length;

	public PickingSize(int width, int height, int length) {
		validateParameters(width, height, length);
		this.width = width;
		this.height = height;
		this.length = length;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getLength() {
		return this.length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + height;
		result = prime * result + width;
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
		PickingSize other = (PickingSize) obj;
		if (length != other.length)
			return false;
		if (height != other.height)
			return false;
		return (width == other.width);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Width: ").append(this.width)
				.append(" Height: ").append(this.height).append(" Length: ")
				.append(this.length).toString();
	}

	private void validateParameters(int width, int height, int length) {
		if (width <= 0)
			throw new IllegalArgumentException(
					"Width cannot be zero or negative");
		if (height <= 0)
			throw new IllegalArgumentException(
					"Height cannot be zero or negative");
		if (length <= 0)
			throw new IllegalArgumentException(
					"Length cannot be zero or negative");
	}
}
