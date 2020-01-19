package pl.edu.agh.mwo.java.crawler;

import java.util.ArrayList;
import java.util.List;

public class SentenceByCharFinder implements SentenceFinder {
	
	private char charToFind;
	private int repeatsToFind;
	
	public SentenceByCharFinder(char charToFind, int repeatsToFind) {
		super();
		this.charToFind = charToFind;
		this.repeatsToFind = repeatsToFind;
	}
	
	@Override
	public String[] findSentences(String[] sentences) {
		
		List<String> result = new ArrayList<>();

		for (String sentence : sentences) {
			int yes = 0;
			for (int j=0; j<sentence.length(); j++) {
				if (sentence.charAt(j) == charToFind) {
					yes++;
				}
			}
			if (yes >= repeatsToFind) {
				result.add(sentence);
			}
		}
		
		String[] _result = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			_result[i] = result.get(i);
		}
		return _result;
	}

}
