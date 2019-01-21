package hu.street.model.domain;

public class Lot {

	private final boolean odd;
	private final int length;
	private final char color;
	private final int number;
	
	public Lot(boolean odd, int length, char color, int number) {
		this.odd = odd;
		this.length = length;
		this.color = color;
		this.number = number;
	}

	public boolean isOdd() {
		return odd;
	}

	public int getLength() {
		return length;
	}

	public char getColor() {
		return color;
	}

	public int getNumber() {
		return number;
	}
	
}
