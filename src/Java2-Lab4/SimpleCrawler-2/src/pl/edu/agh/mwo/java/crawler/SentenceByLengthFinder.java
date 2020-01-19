package pl.edu.agh.mwo.java.crawler;

import java.util.ArrayList;
import java.util.List;

public class SentenceByLengthFinder implements SentenceFinder {

	private int lengthToFind;

	public SentenceByLengthFinder(int lengthToFind) {
		super();
		this.lengthToFind = lengthToFind;
	}

	@Override
	public List<String> findSentences(List<String> sentences) {
		List<String> foundSentences = new ArrayList<>();

		if (lengthToFind == 0) {
			return foundSentences;
		}
		for (String sentence : sentences) {
			if (sentence.length() >= lengthToFind) {
				foundSentences.add(sentence);
			}
		}
		return foundSentences;
	}

}
