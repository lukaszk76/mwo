package pl.edu.agh.mwo.java.crawler;

public class ConsoleResultPrinter {
	public void print(String[] sentences) {
		
		System.out.println();
		for (String sentence : sentences) {
			System.out.println("\n" + sentence);
		}
	}
}
