package cwiczenie2;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String input;

		while (true) {
			System.out.print("Wprowadz tekst lub \"exit\" aby wyjsc >>> ");
			input = scanner.nextLine();
			if (input.equals("exit"))
				break;
			System.out.println(input);
		}

		scanner.close();
	}

}
