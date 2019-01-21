package hu.street.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.street.model.domain.Lot;

public class DataReader {

	private int oddNumber = -1;
	private int evenNumber = 0;
	
	public List<Lot> getLots(String fileName) {
		return parse(read(fileName));
	}
	
	private List<Lot> parse(List<String> lines) {
		return lines.stream().map(this::createLot).collect(Collectors.toList());
	}
	
	private Lot createLot(String line) {
		String[] items = line.split(" ");
		boolean odd = "1".equals(items[0]);
		int length = Integer.parseInt(items[1]);
		char color = items[2].charAt(0);
		int number;
		if(odd) {
			number = oddNumber += 2;
		} else {
			number = evenNumber += 2;
		}
		return new Lot(odd, length, color, number);
	}
	
	private List<String> read(String fileName) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
