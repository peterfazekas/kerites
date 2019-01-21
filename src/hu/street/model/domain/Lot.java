package hu.street.model.domain;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	
	public String printColor() {
		return IntStream.range(0, length).mapToObj(i -> String.valueOf(color)).collect(Collectors.joining());
	}
	
	public String printNumber() {
		String printNum = String.valueOf(number);
		return printNum + IntStream.range(printNum.length(), length)
								.mapToObj(i -> " ")
								.collect(Collectors.joining());
	}
}
