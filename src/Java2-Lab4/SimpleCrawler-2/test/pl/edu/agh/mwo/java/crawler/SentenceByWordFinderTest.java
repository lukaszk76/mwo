package pl.edu.agh.mwo.java.crawler;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SentenceByWordFinderTest {

	@Test
	public void testSimple() {
		SentenceByWordFinder sentenceByWordFinder = new SentenceByWordFinder("s�owo");
		List<String> sentences = Arrays.asList("tu s�owa nie ma", "a tu s�owo jest", "a tu nie jest S�owo");
		
		List<String> results = sentenceByWordFinder.findSentences(sentences);		
		assertEquals(1,  results.size());
	}

	@Test
	public void testEmptyWord() {
		SentenceByWordFinder sentenceByWordFinder = new SentenceByWordFinder("");
		List<String> sentences = Arrays.asList("tu s�owa nie ma", "a tu s�owo jest", "a tu nie jest S�owo");
		
		List<String> results = sentenceByWordFinder.findSentences(sentences);		
		assertEquals(0,  results.size());
	}

	
}
