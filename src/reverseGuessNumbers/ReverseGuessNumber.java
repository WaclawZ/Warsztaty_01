package reverseGuessNumbers;

import java.util.Scanner;

public class ReverseGuessNumber {

	public static void play() {
		Scanner sc = new Scanner(System.in);
		String inputText = "";

		System.out.println("Pomysl liczbe od 0 do 1000, a ja zgadne w max. 10 probach");
		int mini = 0, maxi = 1001, iterator = 1;

		do {
			int guess = (maxi - mini) / 2 + mini;

			if (iterator > 10) {
				System.out.println("OSZUKUJESZ!!!\nNie bede dalej grac :(");
				System.exit(0);
			}

			System.out.println("Zgaduje " + iterator + " raz : " + guess);

			while (true) {
				inputText = sc.next();

				if (inputText.equals("wiecej")) {
					mini = guess;
					break;
				} else if (inputText.equals("mniej")) {
					maxi = guess;
					break;
				} else if (inputText.equals("trafiles"))
					break;
				else {
					System.out.println("Mozesz wpisac tylko \"wiecej\" , \"mniej\" lub \"trafiles\"");
				}
			}
			iterator++;

		} while (!inputText.equals("trafiles"));
		System.out.println("Wygralem z Toba :D");

	}

	public static void main(String[] args) {
		play();
	}

}
