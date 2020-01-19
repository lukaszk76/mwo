package pl.edu.agh.mwo.java.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrawlerStarter {

	public static void main(String[] args) throws IOException {

		Crawler c = new Crawler("http://www.onet.pl", new TextExtractor(), new ConsoleResultsPrinter());

		c.addSentenceFinder(new SentenceByWordFinder("skoczkowie"));
		c.addSentenceFinder(new SentenceByCharCountFinder(10, 'd'));
		c.addSentenceFinder(new SentenceByLengthFinder(912));
		c.addSentenceFinder(new SentenceFinder() {

			@Override
			public List<String> findSentences(List<String> sentences) {
				List<String> foundSentences = new ArrayList<>();
				for (String sentence : sentences) {
					if (sentence.startsWith("W"))
						foundSentences.add(sentence);
				}
				return foundSentences;
			}
		});
		c.addSentenceFinder((sentences) -> {
			List<String> foundSentences = new ArrayList<>();
			for (String sentence : sentences) {
				if (sentence.split(" ").length > 30)
					foundSentences.add(sentence);
			}
			return foundSentences;
		});
		
		c.addSentenceFinder( sentences -> sentences.stream()
				.filter(sentence -> sentence.length() < 30)
				.collect(Collectors.toList()));

		c.run();

	}
}
