package cwiczenie4;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String args[]) throws Exception {
		printFilesSimple("..");
		printLine();
		printFilesDetails("..");
		printLine();
		printFiles("..", ".pdf");
		printLine();
		printTree("..");
	}
	
	private static void printLine() {
		System.out.println("\n---------------------------------------------------------------\n");
	}

	public static void printFilesSimple(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			System.out.println(file.getName());
		}
	}

	public static void printFilesDetails(String path) throws Exception {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd mm:ss");
		for (File file : listOfFiles) {
			BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			String fileName = file.getName();
			String size = null;
			if (attributes.isDirectory()) {
				size = "DIR";
			} else {
				size = String.format("%d", attributes.size());
			}
			String creationDate = df.format(attributes.creationTime().toMillis());
			System.out.println(String.format("%-30s %-20s %s", fileName, size, creationDate));
		}

	}

	public static void printFiles(String path, String extensionFilter) {
		File folder = new File(path);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(extensionFilter);
			}
		};
		File[] listOfFiles = folder.listFiles(filter);

		for (File file : listOfFiles) {
			System.out.println(file.getName());
		}
		
	}

	public static void printTree(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			try {
				BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				System.out.println(file.getPath());
				if (attributes.isDirectory()) {
					printTree(file.getPath());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
