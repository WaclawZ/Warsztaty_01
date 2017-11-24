package guessNumbers;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void zgaduj() {
		Scanner sc = new Scanner(System.in);

		Random r = new Random();
		int number = r.nextInt(101);
		int inputNumber = 0;

		do {
			System.out.print("Zgadnij liczbe : ");

			try {
				inputNumber = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("To nie jest liczba!");
				continue;
			}

			if (inputNumber < number) {
				System.out.println("za malo.");
			} else if (inputNumber > number) {
				System.out.println("za duzo.");
			} else {
				System.out.println("Zgadles!!! :D");
			}

		} while (number != inputNumber);

	}

	public static void main(String[] args) {
		zgaduj();
	}

}
