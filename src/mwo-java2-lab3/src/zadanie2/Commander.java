package zadanie2;
import java.util.Scanner;

import cwiczenie4.*;

public class Commander {
	
	
	
	public static int menu() {
		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		
		while (choice <0 || choice >4) {
			System.out.println("\n<<<<<<<< MENU >>>>>>>>");
			System.out.println("1. List files ");
			System.out.println("2. List files with details");
			System.out.println("3. List files (extension filter)");
			System.out.println("4. Display file tree ");
			System.out.println("0. Exit");
			System.out.print("Your choice? ");
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Wrong choice!");
				scanner.nextLine();
				choice = -1;
			}
		}
		return choice;
	}
	
	public static void main(String args[]) throws Exception {
		String path = "..";
		Scanner scanner = new Scanner(System.in);
		
		int choice;
		while ((choice = menu()) != 0) {
			switch(choice) {
			case 1: {
				Main.printFilesSimple(path);
				break;
			}
			case 2: {
				Main.printFilesDetails(path);
				break;
			}
			case 3: {
				System.out.print("Enter the extension >>> ");
				String extension = scanner.next();
				Main.printFiles(path, extension);
				break;
			}
			case 4: {
				Main.printTree(path);
				break;
			}
			}
		}
	}
		
}
