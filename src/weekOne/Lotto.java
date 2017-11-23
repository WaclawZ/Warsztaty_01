package weekOne;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lotto {

	public static int[] losowanie() {
		Integer[] arr = new Integer[49];
		int[] randomNumbers = new int[6];

		for (int i = 0; i < arr.length; i++)
			arr[i] = i + 1;

		Collections.shuffle(Arrays.asList(arr));

		for (int i = 0; i < randomNumbers.length; i++)
			randomNumbers[i] = arr[i];

		System.out.println("Wylosowane liczby to : " + Arrays.toString(randomNumbers));

		return randomNumbers;
	}

	public static int[] wprowadzanie() {
		Scanner sc = new Scanner(System.in);
		int inputNumber = 0, iterator = 0;
		int[] yourNumbers = { 0, 0, 0, 0, 0, 0 };
		boolean contain = false;

		System.out.println("Wprowadz 6 liczb z zakresu od 1 do 49");

		while (true) {
			try {
				if (1 <= (inputNumber = sc.nextInt()) && (inputNumber) <= 49) {
					for (int element : yourNumbers) {
						if (element == inputNumber)
							contain = true;
					}
					if (!contain) {
						yourNumbers[iterator] = inputNumber;
						iterator++;
						System.out.println("Liczba zostala wprowadzona");
					} else
						System.out.println("Nie mozna wybrac liczby wiecej niz jeden raz");
				} else {
					System.out.println("Liczba musi sie miescic w zakresie.");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("To nie jest Liczba");
				continue;
			}
			contain = false;
			if (iterator == 6) {
				iterator = 0;
				break;
			}
		}

		Arrays.sort(yourNumbers);
		System.out.println("Twoje liczby to : " + Arrays.toString(yourNumbers));

		return yourNumbers;
	}

	public static void trafienia(int[] yourNumbers, int[] randomNumbers) {
		int count = 0;

		for (int i = 0; i < yourNumbers.length; i++) {
			for (int j = 0; j < randomNumbers.length; j++) {
				if (yourNumbers[i] == randomNumbers[j])
					count++;
			}
		}

		if (count == 3)
			System.out.println("Trafiles trojke");
		else if (count == 4)
			System.out.println("Trafiles czworke");
		else if (count == 5)
			System.out.println("Trafiles piatke");
		else if (count == 6)
			System.out.println("Trafiles szostke");
		else
			System.out.println("Niestety nic nie wygrales");

	}

	public static void main(String[] args) {
		int[] yourNumbers = wprowadzanie();
		int[] randomNumbers = losowanie();
		trafienia(yourNumbers, randomNumbers);
	}

}
