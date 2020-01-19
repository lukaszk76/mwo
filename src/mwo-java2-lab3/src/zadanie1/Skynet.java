package zadanie1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Skynet {
	
	private String login = "Reese";
	private String password = "#Terminate";
		
	public static void main(String args[]) {
		Skynet skynet = new Skynet();
		skynet.run();
	}
	
	public void run() {
		readCredentials();
		printSkyNetLogo();
		runLoginPanel();
	}
	
	private void runLoginPanel() {
		String readLogin;
		String readPassword;
		boolean credentialsOK;
		do {
			readLogin = getTextFromInput("Login");
			readPassword = getTextFromInput("Password");
			credentialsOK = verifyCredentials(readLogin, readPassword); 
			if (!credentialsOK) {
				System.out.println("Wrong login or password. Try again.");
			}
		} while (!credentialsOK);
		System.out.println();
		System.out.println("Welcome to SKYNET...");
	}
	
	private String getTextFromInput(String label) {
		System.out.print(label);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	private boolean verifyCredentials(String login, String password) {
		return this.login.equals(login) && this.password.equals(password);
	}
	 
	private void readCredentials() {
		File configFile = new File("res/zadanie1/skynet.conf");
		
		try (BufferedReader reader = Files.newBufferedReader(configFile.toPath())){
			this.login = reader.readLine();
			this.password = reader.readLine();
			System.out.println("INFO System reconfigured...");
		} catch(IOException x) {
			System.out.println("INFO No valid conf data.");
		}
	}
	
	private void printSkyNetLogo() {
		File logoFile = new File ("res/zadanie1/logo.txt");
		String line = null;
		
		try (BufferedReader bufferReader = Files.newBufferedReader(logoFile.toPath())){
			while ((line = bufferReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException x) {
			System.out.println("INFO Could not read logo file.");
		}
	}
}
