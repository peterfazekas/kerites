package hu.street.controler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hu.street.model.domain.Lot;

public class Street {

	private final List<Lot> lots;

	public Street(List<Lot> lots) {
		this.lots = lots;
	}

	public int getLotCount() {
		return lots.size();
	}

	public String getLastLotDetail() {
		Lot lastLot = lots.get(lots.size() - 1);
		return String.format("%nA %s oldalon adták el az utolsó telket.%nAz utolsó telek házszáma: %d",
				lastLot.isOdd() ? "páratlan" : "páros", lastLot.getNumber());
	}

	public String getSameColorFenceInOddSideLotNumber() {
		return String.format("A szomszédossal egyezik a kerítés szine: %d", 
				getSameColorFenceInOddSideLot().getNumber());
	}
	
	private Lot getSameColorFenceInOddSideLot() {
		List<Lot> oddLots = getOddLots();
		Lot result = null;
		int i = 0;
		while(result == null) {
			Lot actualLot = oddLots.get(i);
			if (actualLot.getColor() != ':' && actualLot.getColor() != '#'
					&& actualLot.getColor() == oddLots.get(i + 1).getColor()) {
				result = actualLot;
			}
			i++;
		}
		return result;
	}

	private List<Lot> getOddLots() {
		return lots.stream().filter(Lot::isOdd).collect(Collectors.toList());
	}

	public String getFenceColorByNumber(int number) {
		return getLotByNumber(number)
				.map(Lot::getColor)
				.map(i -> String.format("A kerítés színe / állapota: %c", i))
				.orElse("Nincs ilyen telek.");
	}
	
	public String getNewColorProposal(int number) {
		return String.format("Egy lehetséges festési szín: %c", getNewColor(number));
	}
	
	private char getNewColor(int number) {
		List<Integer> mustNotBe = new ArrayList<>();
		getColor(number - 2).ifPresent(i -> mustNotBe.add(colorToCode(i)));
		getColor(number).ifPresent(i -> mustNotBe.add(colorToCode(i)));
		getColor(number + 2).ifPresent(i -> mustNotBe.add(colorToCode(i)));
		return codeToColor(generateRandomColor(mustNotBe));
	}
	
	private int colorToCode(char color) {
		return (int) color - 65;
	}
	
	private char codeToColor(int code) {
		return (char)(code + 65);
	}
	
	private int generateRandomColor(List<Integer> mustNotBe) {
		List<Integer> possibleColorCodes = new ArrayList<>();
		IntStream.range(0, 26).forEach(possibleColorCodes::add);
		mustNotBe.forEach(i -> possibleColorCodes.remove(i));
		Random random = new Random();
		return possibleColorCodes.get(random.nextInt(possibleColorCodes.size()));
	}
	
	private Optional<Character> getColor(int number) {
		Optional<Character> optionalColor = getLotByNumber(number).map(Lot::getColor);
		if (optionalColor.isPresent() && (optionalColor.get() == '#' || optionalColor.get() == ':')) {
			optionalColor = Optional.empty();
		}
		return optionalColor;
	}
	
	private Optional<Lot> getLotByNumber(int number) {
		return lots.stream().filter(i -> i.getNumber() == number).findAny();
	}
	
	public List<String> printOddSideDetails() {
		return Arrays.asList(printOddSideFenceColors(), printOddSideNumbers());
	}
	
	private String printOddSideFenceColors() {
		return getOddLots().stream().map(Lot::printColor).collect(Collectors.joining());
	}
	
	private String printOddSideNumbers() {
		return getOddLots().stream().map(Lot::printNumber).collect(Collectors.joining());
	}
}
