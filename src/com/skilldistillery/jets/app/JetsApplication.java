package com.skilldistillery.jets.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.skilldistillery.jets.util.GetInputUtility;

public class JetsApplication {

	private AirField airField;

	// ANSI color codes - Better terminal menu view and grading pleasure!

	private String ANSI_RESET = "\u001B[0m";
	private String ANSI_RED = "\u001B[31m";
	private String ANSI_GREEN = "\u001B[32m";
	private String ANSI_YELLOW = "\u001B[33m";
	private String ANSI_PURPLE = "\u001B[35m";
	private String ANSI_CYAN = "\u001B[36m";

	private String[] menuChoices = { "List fleet", "Fly all jets", "View fastest jet", "View jet with longest range",
			"Load all Cargo Jets", "Dogfight!", "Add a jet to Fleet", "Remove a jet from Fleet", "Quit" };

	public static void main(String[] args) {

		JetsApplication app = new JetsApplication();
		app.launch();
	}

	private void launch() {

		Scanner keyboard = new Scanner(System.in);

		airField = new AirField();

		airField.loadJetsFromFile();

		interactiveMenu(keyboard);

		keyboard.close();

	}

	private void interactiveMenu(Scanner keyboard) {

		int currentChoice = 0;

		GetInputUtility giu = new GetInputUtility(); // validates and only returns type in range

		int minInt = 1;
		int maxInt = menuChoices.length;

		do {

			System.out.println(ANSI_GREEN + "\n\nMain Menu\n" + ANSI_RESET);

			System.out.print(ANSI_YELLOW);

			currentChoice = giu.getInput(menuChoices, minInt, maxInt, keyboard); // only returns type in range

			System.out.println("\n\n" + ANSI_CYAN);

			switch (currentChoice) {
			case 1:
				System.out.println("List Fleet\n" + ANSI_RESET);
				airField.listFleet();
				break;
			case 2:
				System.out.println("Fly all Jets\n" + ANSI_RESET);
				airField.flyAllJets(); // in AirField - execute appropriate method for each
				break;
			case 3:
				System.out.println("View Fastest Jet(s)\n" + ANSI_RESET);
				airField.viewFastestJet(); // in AirField - execute appropriate method for each
				break;
			case 4:
				System.out.println("View Jet(s) with Longest Range\n" + ANSI_RESET);
				airField.viewJetWithLongestRange(); // in AirField - execute appropriate method for each
				break;
			case 5:
				System.out.println("Load all Cargo Jets\n" + ANSI_RESET);
				airField.loadAllCargoJets(); // in AirField - execute appropriate method for each
				break;
			case 6:
				System.out.println("Dogfight all Fighter Jets\n" + ANSI_RESET);
				airField.dogFight(); // in AirField - execute appropriate method for each
				break;
			case 7:
				addJetToFleet(giu, keyboard);
				break;
			case 8:
				System.out.println("Remove Jet from Fleet\n" + ANSI_RESET);
				removeJetFromFleet(giu, keyboard);
				break;
			case 9:
				System.out.println("Quit\n" + ANSI_RESET);
				quit();
				return;
			default:
				break;
			}

			System.out.print(ANSI_RESET);

		} while (currentChoice != maxInt);

	}

	private void displayProperties(int type, String model, double speed, int range, long price) {
		System.out.printf("Type:  %s%n", type);
		System.out.printf("Model: %s%n", model);
		System.out.printf("Speed: %.2f%n", speed);
		System.out.printf("Range: %d%n", range);
		System.out.printf("Price: $%d%n", price);
	}

	private void addJetToFleet(GetInputUtility giu, Scanner keyboard) {

		System.out.println(ANSI_CYAN + "\n\nAdd Jet to Fleet\n" + ANSI_PURPLE);

		String[] typeOfJetChoices = { "CargoPlane", "FighterJet", "PassengerJet", "Back (Changed My Mind)" };

		int yourTypeOfJet = giu.getInput(typeOfJetChoices, 1, 4, keyboard); // only returns type in range

		if (yourTypeOfJet == 4) {
			return;
		}

		System.out.println(ANSI_GREEN + "\n\nPlease Enter the Following Information\n" + ANSI_RESET);

		String yourModel = giu.getInput("Model: (ABCd123): ", "", "\uffff".repeat(255), keyboard, 1, 255);
		double yourSpeed = giu.getInput("Speed: (####.##): ", (double) 1, Double.MAX_VALUE, keyboard);
		int yourRange = giu.getInput("Range: (#######): ", (int) 1, Integer.MAX_VALUE, keyboard);
		long yourPrice = giu.getInput("Price: (#######): ", (long) 1, Long.MAX_VALUE, keyboard);

		System.out.println(ANSI_YELLOW + "\n\nPlease Review the Following Information\n" + ANSI_RESET);

		displayProperties(yourTypeOfJet, yourModel, yourSpeed, yourRange, yourPrice);

		System.out.println(ANSI_GREEN + "\n\nPlease Make your Choice Carefully\n" + ANSI_RESET);

		int areYouSure = giu.getInput("Are you sure you want to add this jet ? (1) Yes (2) No ? ", 1, 2, keyboard);

		if (areYouSure == 1) {
			System.out.println("\n\nAdding jet to fleet...");
			boolean wasSuccessfullyAdded = airField.addJetToAirField(typeOfJetChoices[yourTypeOfJet - 1], yourModel,
					yourSpeed, yourRange, yourPrice);
			if (wasSuccessfullyAdded) {
				System.out.println("\n\nJet added to fleet!");
			} else {
				System.out.println("\n\nJet not added to fleet.");
			}
		}

	}

	private void removeJetFromFleet(GetInputUtility giu, Scanner keyboard) {

		System.out.println(ANSI_GREEN + "\n\nPlease Make your Choice Carefully\n" + ANSI_RESET);

		List<String> listOfJets = airField.getFleetList();
		String[] jetsMenuChoices = listOfJets.toArray(new String[listOfJets.size() + 1]);
		jetsMenuChoices[listOfJets.size()] = "Back (Changed My Mind)";

		int minInt = 1;
		int maxInt = jetsMenuChoices.length;

		int removeJetChoice = giu.getInput(jetsMenuChoices, minInt, maxInt, keyboard);

		if (removeJetChoice >= maxInt) {
			System.out.println(ANSI_RED + "\n\nAborted - No jet removed from fleet." + ANSI_RESET);
			return;
		}

		boolean removedResult = airField.removeJetFromFleet(removeJetChoice - 1);

		if (removedResult) {
			System.out.println(ANSI_RED + "\n\nJet removed from fleet." + ANSI_RESET);
		} else {
			System.out.println(ANSI_RED + "\n\nJet not removed from fleet." + ANSI_RESET);
		}
	}

	private void quit() {
		System.out.println(ANSI_PURPLE + "G o o d   B y e !" + ANSI_RESET);
	}

}
