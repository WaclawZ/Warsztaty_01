package Cubes;

import java.util.Random;
import java.util.StringTokenizer;

public class Cube {

	public static void diceGame(String str) {
		StringTokenizer strTok = new StringTokenizer(str, "D+-");
		int kindOfCube = 0, rollNumber = 1, mod = 0;
		char symbol = ' ';

		if (str.contains("+"))
			symbol = '+';
		else if (str.contains("-"))
			symbol = '-';

		if (str.contains("+") || str.contains("-")) {
			if (strTok.countTokens() == 2) {
				kindOfCube = Integer.parseInt(strTok.nextToken());
				mod = Integer.parseInt(strTok.nextToken());
			} else {
				rollNumber = Integer.parseInt(strTok.nextToken());
				kindOfCube = Integer.parseInt(strTok.nextToken());
				mod = Integer.parseInt(strTok.nextToken());
			}
		} else if (strTok.countTokens() == 2) {
			rollNumber = Integer.parseInt(strTok.nextToken());
			kindOfCube = Integer.parseInt(strTok.nextToken());
		} else {
			kindOfCube = Integer.parseInt(strTok.nextToken());
		}
		System.out.println(rollNumber + " " + kindOfCube + " "+ symbol + mod);

		roll(rollNumber, kindOfCube, mod, symbol);
	}

	public static void roll(int number, int dice, int mod, char s) {
		System.out.println("Symulacja rzutu");

		Random r = new Random();
		int score = 0, result = 0;

		for (int i = 0; i < number; i++) {
			score += r.nextInt(dice) + 1;
			
			System.out.println("Rzut nr " + (i + 1) + " : " + score);
			result += score;
			score = 0;
		}
		if (s == '+')
			result += mod;
		else if (s == '-')
			result -= mod;

		System.out.println("Wynik symulacji : " + result);
	}

	public static void main(String[] args) {
		String str = "2D10+5";

		diceGame(str);
	}

}
