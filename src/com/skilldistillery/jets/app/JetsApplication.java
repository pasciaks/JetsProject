package com.skilldistillery.jets.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.skilldistillery.jets.entities.Jet;
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

	private String cargoPlane = "\uD83D\uDEEC"; // 🛬
	private String fighterJet = "\uD83D\uDEE9"; // 🛩
	private String passengerPlane = "\uD83D\uDEEB"; // 🛫

	private String allPlanes = cargoPlane + " " + passengerPlane + " " + fighterJet;

	private String[] menuChoices = { "List fleet", "Fly all jets", "View fastest jet", "View jet with longest range",
			"Load all Cargo Jets", "Dogfight!", "Add a jet to Fleet", "Remove a jet from Fleet", "Quit" };

	public static void main(String[] args) {

		JetsApplication app = new JetsApplication();
		app.launch();
	}

	private void launch() {

		System.out.println(ANSI_PURPLE + "Welcome to the Jets Application!" + ANSI_RESET);

		Scanner keyboard = new Scanner(System.in);

		airField = new AirField();

		airField.loadJetsFromFile("jets.txt");

		interactiveMenu(keyboard);

		keyboard.close();

	}

	private void interactiveMenu(Scanner keyboard) {

		int currentChoice = 0;

		GetInputUtility giu = new GetInputUtility(); // validates and only returns type in range

		int minInt = 1;
		int maxInt = menuChoices.length;

		do {

			System.out.println(ANSI_GREEN + " M a i n    M e n u " + ANSI_YELLOW);

			currentChoice = giu.getInput(menuChoices, minInt, maxInt, keyboard); // only returns type in range

			System.out.print(ANSI_CYAN);

			switch (currentChoice) {
			case 1:
				System.out.println(allPlanes + " List Fleet" + ANSI_RESET);
				airField.listFleet();
				break;
			case 2:
				System.out.println("Fly all Jets" + ANSI_RESET);
				airField.flyAllJets(); // in AirField - execute appropriate method for each
				break;
			case 3:
				System.out.println("View Fastest Jet(s)" + ANSI_RESET);
				airField.viewFastestJet(); // in AirField - execute appropriate method for each
				break;
			case 4:
				System.out.println("View Jet(s) with Longest Range" + ANSI_RESET);
				airField.viewJetWithLongestRange(); // in AirField - execute appropriate method for each
				break;
			case 5:
				System.out.println(cargoPlane + " Load all Cargo Jets" + ANSI_RESET);
				airField.loadAllCargoJets(); // in AirField - execute appropriate method for each
				break;
			case 6:
				System.out.println(fighterJet + " Dogfight all Fighter Jets" + ANSI_RESET);
				airField.dogFight(); // in AirField - execute appropriate method for each
				break;
			case 7:
				addJetToFleet(giu, keyboard);
				break;
			case 8:
				System.out.println("Remove Jet from Fleet" + ANSI_RESET);
				removeJetFromFleet(giu, keyboard);
				break;
			case 9:
				System.out.println("Quit" + ANSI_RESET);
				quit();
				return;
			default:
				break;
			}

			System.out.print(ANSI_RESET);

		} while (currentChoice != maxInt);

	}

	private void displayProperties(String[] arr, int type, String model, double speed, int range, long price) {

		System.out.printf("Type:  %s%n", arr[type - 1]);
		System.out.printf("Model: %s%n", model);
		System.out.printf("Speed: %.2f%n", speed);
		System.out.printf("Range: %d%n", range);
		System.out.printf("Price: $%d%n", price);
	}

	private void addJetToFleet(GetInputUtility giu, Scanner keyboard) {
		String[] typeOfJetChoices = { "CargoPlane", "FighterJet", "PassengerJet", "Back (Changed My Mind)" };

		System.out.println(ANSI_CYAN + "\n\nAdd Jet to Fleet\n" + ANSI_PURPLE);
		int yourTypeOfJet = giu.getInput(typeOfJetChoices, 1, 4, keyboard); // only returns type in range
		if (yourTypeOfJet == 4) {
			return;
		}

		System.out.println(ANSI_RED + "\nAdding a new: " + typeOfJetChoices[yourTypeOfJet - 1] + ANSI_RESET);
		System.out.println(ANSI_GREEN + "\n\nPlease Enter the Following Information\n" + ANSI_RESET);

		String yourModel = giu.getInput("Model (50 Character max allowed): (ABCd123)  : ", "", "\uffff".repeat(50),
				keyboard, 1, 50);
		double yourSpeed = giu.getInput("Speed as Numbers only for MPH: (####.##)     : ", (double) 1, Double.MAX_VALUE,
				keyboard);
		int yourRange = giu.getInput("Range as Numbers only for Miles: (#######)   : ", (int) 1, Integer.MAX_VALUE,
				keyboard);
		long yourPrice = giu.getInput("Price as Numbers only for Dollars (#######)  : ", (long) 1, Long.MAX_VALUE,
				keyboard);

		System.out.println(ANSI_YELLOW + "\n\nPlease Review the Following Information\n" + ANSI_RESET);

		displayProperties(typeOfJetChoices, yourTypeOfJet, yourModel, yourSpeed, yourRange, yourPrice);

		System.out.println(ANSI_GREEN + "\n\nPlease Make your Choice Carefully\n" + ANSI_RESET);
		int areYouSure = giu.getInput("Are you sure you want to add this jet ? (1) Yes (2) No ? ", 1, 2, keyboard);

		if (areYouSure == 1) {
			System.out.println("\n\nAdding jet to fleet...");
			Jet addedJet = airField.addJetToAirField(typeOfJetChoices[yourTypeOfJet - 1], yourModel, yourSpeed,
					yourRange, yourPrice);
			if (addedJet != null) {
				System.out.println("\n\nJet added to fleet!");
			} else {
				System.out.println("\n\nJet not added to fleet.");
			}
		}

	}

	private void removeJetFromFleet(GetInputUtility giu, Scanner keyboard) {

		List<String> listOfJets = airField.getFleetStringList();

		if (listOfJets.isEmpty()) {
			System.out.println(ANSI_RED + "\n\nNo jets in fleet to remove." + ANSI_RESET);
			return;
		}

		System.out.println(ANSI_GREEN + "\n\nPlease Make your Choice Carefully\n" + ANSI_RESET);
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
