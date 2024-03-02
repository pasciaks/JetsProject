package com.skilldistillery.jets.app;

import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.skilldistillery.jets.util.GetInputUtility;

public class JetsApplication {

	private AirField airField;

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

			currentChoice = giu.getInput(menuChoices, minInt, maxInt, keyboard); // only returns type in range

			switch (currentChoice) {
			case 1:
				airField.listFleet();
				break;
			case 2:
				airField.flyAllJets(); // in AirField - execute appropriate method for each
				break;
			case 3:
				airField.viewFastestJet(); // in AirField - execute appropriate method for each
				break;
			case 4:
				airField.viewJetWithLongestRange(); // in AirField - execute appropriate method for each
				break;
			case 5:
				airField.loadAllCargoJets(); // in AirField - execute appropriate method for each
				break;
			case 6:
				airField.dogFight(); // in AirField - execute appropriate method for each
				break;
			case 7:
				addJetToFleet(giu, keyboard);
				break;
			case 8:
				removeJetFromFleet();
				break;
			case 9:
				quit();
				return;
			default:
				break;
			}

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

		System.out.println("\n\nAdd Jet to Fleet\n");

		String[] typeOfJetChoices = { "CargoPlane", "FighterJet", "JetImpl", "Back (Changed My Mind)" };

		int yourTypeOfJet = giu.getInput(typeOfJetChoices, 1, 4, keyboard); // only returns type in range

		if (yourTypeOfJet == 4) {
			return;
		}

		String yourModel = giu.getInput("Model: (ABC123): ", "", "\uffff".repeat(255), keyboard, 1, 255);
		double yourSpeed = giu.getInput("Speed: (####.##)", (double) 1, Double.MAX_VALUE, keyboard);
		int yourRange = giu.getInput("Range: (####)", (int) 1, Integer.MAX_VALUE, keyboard);
		long yourPrice = giu.getInput("Price: (####)", (long) 1, Long.MAX_VALUE, keyboard);

		displayProperties(yourTypeOfJet, yourModel, yourSpeed, yourRange, yourPrice);

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

	private void removeJetFromFleet() {
		// get the fleet or display fleet using airfield
		// menu to choose which jet
		// valid menu choice
		// call the airfield method to remove jet
	}

	private void quit() {
		System.out.println("Good Bye!");
	}

}
