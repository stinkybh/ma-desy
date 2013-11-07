package madesy.model;

import java.util.Random;

public class PickingSize {
	private static Random random = new Random();
	
	public static PickingSize generateRandomPickingSize() {
		return new PickingSize(random.nextInt(100) + 1, 
				random.nextInt(100) + 1, random.nextInt(100) + 1);
	}
	
	private int width;
	private int height;
	private int depth;
	
	public PickingSize(int width, int height, int depth) {
		validateParameters(width, height, depth);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getDepth() {
		return this.depth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depth;
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
		if (depth != other.depth)
			return false;
		if (height != other.height)
			return false;
		return (width == other.width);
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("Width: ")
			.append(this.width)
			.append(" Height: ")
			.append(this.height)
			.append(" Depth: ")
			.append(this.depth)
			.toString();
	}
	
	private void validateParameters(int width, int height, int depth) {
		if (width <= 0)
			throw new IllegalArgumentException(
					"Width cannot be zero or negative");
		if (height <= 0)
			throw new IllegalArgumentException(
					"Height cannot be zero or negative");
		if (depth <= 0)
			throw new IllegalArgumentException(
					"Depth cannot be zero or negative");
	}
}
