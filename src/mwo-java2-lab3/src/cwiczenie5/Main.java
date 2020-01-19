package cwiczenie5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String args[]) throws Exception {

		Bureau bureau = initBureau();
		printBureau(bureau);
		saveBureau(bureau, "mi6.ser");
		Bureau loadedBureau = loadBureau("mi6.ser");
		printBureau(loadedBureau);

	}

	private static void saveBureau(Bureau bureau, String filename) throws Exception {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bureau);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private static Bureau loadBureau(String filename) throws Exception {

		Bureau bureau = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bureau = (Bureau) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		return bureau;
	}

	private static Bureau initBureau() {
		Bureau bureau = new Bureau("MI6");
		Agent agent007 = new Agent("James", "Bond", 35, "007");
		bureau.addAgent(agent007);

		Task task = new Task("darkMamba", "infiltrate, spy, destroy");
		agent007.addTask(task);

		return bureau;
	}

	private static void printBureau(Bureau bureau) {
		System.out.println("Bureau name: " + bureau.getName());
		for (Agent agent : bureau.getAgents()) {
			System.out.println("Agent: " + agent.getName() + " " + agent.getSurname() + ", age: " + agent.getAge()
					+ " , code: " + agent.getNumber());
			for (Task task : agent.getTasks()) {
				System.out.print("Task: " + task.getName() + " - decription: " + task.getDescription() + " - status: ");
				if (task.isAcomplished()) {
					System.out.println(" acomplished.");
				} else {
					System.out.println(" not acomplished.");
				}
			}
		}
	}

}
