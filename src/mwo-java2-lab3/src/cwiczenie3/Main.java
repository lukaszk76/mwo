package cwiczenie3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static String[] stringsToBeWritten = { "Joh Woo 001", "Robert Duval 002", "James Bond 007" };

	public static void main(String args[]) throws Exception {
		String fileName = "res/cwiczenie3/special-agents.txt";

		saveAgents(stringsToBeWritten, fileName, false);
		insertAgents(stringsToBeWritten, fileName);
		createDirectories();
		copyFiles();
	}

	private static void saveAgents(String[] agents, String filename, boolean append) throws Exception {
		PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(filename), append));
		for (String agent : agents) {
			printWriter.println(agent);
		}
		printWriter.close();
	}

	private static void insertAgents(String[] agents, String filename) throws Exception {
		RandomAccessFile raf = new RandomAccessFile(filename, "rw");
		raf.seek(0);

		for (String agent : agents) {
			raf.writeBytes(agent + '\n');
		}

		raf.seek(raf.length() / 2);
		for (String agent : agents) {
			raf.writeBytes(agent + '\n');
		}
		raf.close();
	}

	private static void createDirectories() {

		new File("agents/active-agents").mkdirs();
		new File("agents/missing-agents").mkdirs();
	}

	private static void copyFiles() {
		Path sourcePath = Paths.get("res/cwiczenie3/special-agents.txt");
		Path targetPath = Paths.get("agents/active-agents/special_agents.txt");
		Path pathToBeDeleted = Paths.get("agents/missing-agents");

		try {
			Files.copy(sourcePath, targetPath);
		} catch (FileAlreadyExistsException x) {
			System.out.println("File already exists!");
		} catch (IOException x) {
			x.printStackTrace();
		}

		try {
			Files.delete(pathToBeDeleted);
		} catch (IOException x) {
			x.printStackTrace();
		}
	}
}
