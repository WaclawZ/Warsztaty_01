package popularWords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class ValueComparator implements Comparator<String> {
	Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	@Override
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}
}

public class PopularWords {

	public static void downloadTitles(String fileName) {
		Path p = Paths.get(fileName);
		ArrayList<String> save = new ArrayList<>();

		Connection connect = Jsoup.connect("http://www.onet.pl/");
		try {
			Document document = connect.get();
			Elements links = document.select("span.title");
			for (Element elem : links) {
				save.add(elem.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (!Files.exists(p)) {
				Files.createFile(p);
				System.out.println("Plik zostal utworzony");
			}
			Files.write(p, save);
			System.out.println("Plik zostal zapisany");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteSmallWords(String fileName) {
		Path p = Paths.get(fileName);
		String str = "", toSave = "";
		ArrayList<String> save = new ArrayList<>();

		try {
			if (Files.exists(p)) {
				for (String lines : Files.readAllLines(p))
					str += lines + "\n";
			}
		} catch (IOException e) {
			System.out.println("Cos poszlo nie tak");
		}

		String[] part1 = str.split("\n");

		for (int i = 0; i < part1.length; i++) {
			StringTokenizer sT = new StringTokenizer(part1[i], ":.,;\"!?- ");

			while (sT.hasMoreTokens()) {
				String token = sT.nextToken().toLowerCase();
				if (token.length() > 3)
					toSave += token + " ";
			}
			save.add(toSave);
			toSave = "";
		}
		try {
			Files.write(p, save);
			System.out.println("Plik zostal zapisany");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void mostPopular(String fileName, String fileToWrite) {
		Path p = Paths.get(fileName);
		Path p1 = Paths.get(fileToWrite);

		Map<String, Integer> map = new TreeMap<>();
		ValueComparator comp = new ValueComparator(map);
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(comp);

		String str = "";

		try {
			if (Files.exists(p)) {
				for (String lines : Files.readAllLines(p))
					str += lines + " ";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringTokenizer strTok = new StringTokenizer(str, "\n ");

		while (strTok.hasMoreTokens()) {
			String element = strTok.nextToken();
			if (map.containsKey(element))
				map.put(element, map.get(element) + 1);
			else
				map.put(element, 1);
		}

		sortedMap.putAll(map);

		Iterator<Map.Entry<String, Integer>> it = sortedMap.entrySet().iterator();
		ArrayList<String> save = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Map.Entry<String, Integer> res = it.next();
			save.add(res.getKey());
		}

		try {
			if (!Files.exists(p1)) {
				Files.createFile(p1);
				System.out.println("Plik zostal utworzony");
			}
			Files.write(p1, save);
			System.out.println("Plik zostal zapisany");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String fileName = "popular_words.txt", fileName2 = "most_popular_words.txt";

		downloadTitles(fileName);
		deleteSmallWords(fileName);
		mostPopular(fileName, fileName2);
	}

}
