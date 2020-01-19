package pl.edu.agh.mwo.java.crawler;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler {

	protected String urlToProcess;
	protected TextExtractor textExtractor = new TextExtractor();
	protected ConsoleResultPrinter consoleResultPrinter = new ConsoleResultPrinter();

	public Crawler(String initUrl) throws MalformedURLException {
		urlToProcess = initUrl;
	}

	public void run() throws IOException {

		Document doc = null;
		doc = Jsoup.connect(urlToProcess).get();

		String[] sentences = textExtractor.extractText(doc);

		SentenceFinder wordFinder = new SentenceByWordFinder("skoczkowie");
		String[] foundByWordSentences = wordFinder.findSentences(sentences);

		consoleResultPrinter.print(foundByWordSentences);

		SentenceFinder charFinder = new SentenceByCharFinder('p', 10);
		String[] foundByCharSentences = charFinder.findSentences(sentences);

		consoleResultPrinter.print(foundByCharSentences);
	}
}
