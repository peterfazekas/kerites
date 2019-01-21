package hu.street;

import hu.street.controler.Street;
import hu.street.model.service.Console;
import hu.street.model.service.DataReader;

public class App {

	private final Street street;
	private final Console console;
	
	public App() {
		DataReader data = new DataReader();
		street = new Street(data.getLots("kerites.txt"));
		console = new Console();
	}
	
	public static void main(String[] args) {
		new App().run();
	}

	private void run() {
		System.out.println("2. feladat: Az eladott telkek száma: " + street.getLotCount());
		System.out.println("3. feladat:" + street.getLastLotDetail());
		System.out.println("4. feladat: " + street.getSameColorFenceInOddSideLotNumber());
		int number = console.readInt("5. feladat: Adjon meg egy házszámot: ");
		System.out.println(street.getFenceColorByNumber(number));
		System.out.println(street.getNoewColorProposal(number));
	}
}
