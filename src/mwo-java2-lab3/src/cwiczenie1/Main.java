package cwiczenie1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws Exception {
//		scanFile("res/cwiczenie1/poem.txt", "we");
//		readFile("res/cwiczenie1/poem.txt", "we", "was");
		readChars("res/cwiczenie1/poem.txt");
	}

	private static void scanFile(String filename, String keyword) throws Exception {
		File poemFile = new File(filename);
		Scanner scanner = new Scanner(poemFile);
		while (scanner.hasNext()) {
			String next = scanner.next();
			if (next.contains(keyword))
				System.out.println(next);
		}
		scanner.close();
		System.out.println("Path: " + poemFile.getPath());
		System.out.println("Free space: " + poemFile.getFreeSpace() / (1024 * 1024) + " MB");
		System.out.println("Free space: " + poemFile.getFreeSpace() / (1024 * 1024 * 1024) + " GB");
	}

	private static void readFile(String filename, String... keywords) {
		File poemFile = new File(filename);

		try (BufferedReader reader = Files.newBufferedReader(poemFile.toPath())) {
			String line = null;
			int lineNumber = 1;
			int hitsCount = 0;
			while ((line = reader.readLine()) != null) {
				for (String keyword : keywords) {
					if (line.contains(keyword)) {
						System.out.println(lineNumber + " " + line);
						hitsCount++;
						break;
					}
				}
				lineNumber++;
			}
			System.out.println("Found in " + hitsCount + " lines.");
		} catch (IOException x) {
			System.err.format("IOException: %s", x);
		}
	}

	private static void readChars(String filename) {

		File poemFile = new File(filename);

		try (BufferedReader reader = Files.newBufferedReader(poemFile.toPath())) {
			String line = null;
			int charRead = -1;
			int charsNumber = 0;
			int linesNumber = 0;

			while ((charRead = reader.read()) != -1) {

				charsNumber++;
				if ((char) charRead == '\n') {
					linesNumber++;
				}

			}
			System.out.println("Total characters number " + charsNumber);
			System.out.println("Total lines number " + linesNumber);
		} catch (IOException x) {
			System.err.format("IOException: %s", x);
		}
	}

}
