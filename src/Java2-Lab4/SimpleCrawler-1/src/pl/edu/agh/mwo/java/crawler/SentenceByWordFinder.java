package pl.edu.agh.mwo.java.crawler;

import java.util.ArrayList;
import java.util.List;

public class SentenceByWordFinder implements SentenceFinder {
	String wordToFind;

	public SentenceByWordFinder(String wordToFind) {
		super();
		this.wordToFind = wordToFind;
	}

	@Override
	public String[] findSentences(String[] sentences) {
		List<String> result = new ArrayList<>();

		for (String sentence : sentences) {
			if (sentence.contains(wordToFind)) {
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
