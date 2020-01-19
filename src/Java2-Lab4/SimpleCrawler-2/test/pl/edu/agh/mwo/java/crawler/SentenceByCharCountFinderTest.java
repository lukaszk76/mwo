package pl.edu.agh.mwo.java.crawler;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SentenceByCharCountFinderTest {

	@Test
	public void testOne() {
		SentenceByCharCountFinder sentenceByCharCountFinder = new SentenceByCharCountFinder(1, 'w');
		List<String> sentences = Arrays.asList("aaaawaaaa", "aaaa-aaaa");
		
		List<String> results = sentenceByCharCountFinder.findSentences(sentences);		
		assertEquals(1,  results.size());
	}
	
	@Test
	public void testOneUtf() {
		SentenceByCharCountFinder sentenceByCharCountFinder = new SentenceByCharCountFinder(1, '�');
		List<String> sentences = Arrays.asList("aaaawaa�aa", "aaaa��aaaa", "aallla", "aa��Aaa");
		
		List<String> results = sentenceByCharCountFinder.findSentences(sentences);
		assertEquals(2,  results.size());
		assertEquals("aaaawaa�aa", sentences.get(0));
		assertEquals("aaaa��aaaa", sentences.get(1));
	}


}
