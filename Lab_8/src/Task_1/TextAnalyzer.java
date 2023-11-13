package Task_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer {
	// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	// load words in the text file given by fileName and store into map by using add
	// method in Task 2.1.
	// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		int index = 1;
		while (true) {
			line = reader.readLine();

			if (line == null)
				break;
			StringTokenizer tokens = new StringTokenizer(line, " ");

			while (tokens.hasMoreTokens()) {
				String word = tokens.nextToken();
				if (tokens.hasMoreTokens()) {
					add(word, index);
				} else {
					add(word, -index);
				}
				index++;
			}
		}
		System.out.println(map);
		reader.close();

	}
	// In the following method, if the word is not in the map, then adding that word
	// to the map containing the position of the word in the file. If the word is
	// already in the map, then its word position is added to the list of word
	// positions for this word.
	// Remember to negate the word position if the word is at the end of a line in
	// the text file

	public void add(String word, int position) {
		
		if (!map.containsKey(word)) {
			ArrayList<Integer> result = new ArrayList<>();
			result.add(position);
			map.put(word, result);
		} else {
			ArrayList<Integer> result = map.get(word);
			if (position < 0) {
				position = -position;
			}
			result.add(position);
		}

	}

	// This method should display the words of the text file along with the
	// positions of each word, one word per line, in alphabetical order
	public void displayWords() {
		
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " ");
			ArrayList<Integer> position = entry.getValue();
			for (Integer result : position) {
				System.out.print(result + " ");
			}
			System.out.println();
		}

	}

	// This method will display the content of the text file stored in the map
	public void displayText() {
		
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			System.out.println(entry.getKey());
		}

	}

	// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		int maxFrequent = 0;
		String mostFrequent = null;
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			int frequent = entry.getValue().size();
			if (frequent > maxFrequent) {
				maxFrequent = frequent;
				mostFrequent = entry.getKey();
			}
		}

		return mostFrequent;
	}

	public static void main(String[] args) throws IOException {
		TextAnalyzer t = new TextAnalyzer();
		String filePath = "data/fit.txt";
		t.load(filePath);

		System.out.println("Cac tu voi vi tri cua tu:");
		t.displayWords();

		System.out.println("Tat ca tu trong doan:");
		t.displayText();

		System.out.println("Tu lap lai nhieu nhat: " + t.mostFrequentWord());

	}

}
