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

		interactiveMenu(keyboard);

		keyboard.close();

	}

	private void interactiveMenu(Scanner keyboard) {

		int currentChoice = 0;

		// This implementation is a little complex
		// However, it serves as a chance to use my getInputUtility

		GetInputUtility giu = new GetInputUtility(); // validates and only returns type in range

		int minInt = 1;
		int maxInt = menuChoices.length;

		do {

			currentChoice = giu.getInput(menuChoices, minInt, maxInt, keyboard); // only returns type in range

			switch (currentChoice) {
			case 1:
				listFleet();
				break;
			case 2:
				flyAllJets();
				break;
			case 3:
				viewFastestJet();
				break;
			case 4:
				viewJetWithLongestRange();
				break;
			case 5:
				loadAllCargoJets();
				break;
			case 6:
				dogFight();
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

	private void listFleet() {
		System.out.println("List Fleet");

	}

	private void flyAllJets() {
		System.out.println("Fly All Jets");

	}

	private void viewFastestJet() {
		System.out.println("View Fastest Jet");

	}

	private void viewJetWithLongestRange() {
		System.out.println("View Jet with Longest Range");

	}

	private void loadAllCargoJets() {
		System.out.println("Load All Cargo Jets");

	}

	private void dogFight() {
		System.out.println("Dog Fight");
	}

	private void addJetToFleet(GetInputUtility giu, Scanner keyboard) {

		System.out.println("Add Jet to Fleet");

		// validated user input for

		// type
		// model (String)
		// speed (double)
		// range (int)
		// price (long)

		String[] typeOfJetChoices = { "Cargo Plane", "Fighter Jet", "Jet Impl", "Changed My Mind" };

		int yourTypeOfJet = giu.getInput(typeOfJetChoices, 1, 4, keyboard); // only returns type in range

		if (yourTypeOfJet == 4) {
			return;
		}

		String yourModel = giu.getInput("Model:", "", "\uffff".repeat(255), keyboard, 1, 255); // enforce length 1- 255
		double yourSpeed = giu.getInput("Speed:", (double) 1, Double.MAX_VALUE, keyboard);
		int yourRange = giu.getInput("Range:", (int) 1, Integer.MAX_VALUE, keyboard);
		long yourPrice = giu.getInput("Price:", (long) 1, Long.MAX_VALUE, keyboard);

		// call the airfield add jet method

		airField.addJet(typeOfJetChoices[yourTypeOfJet - 1], yourModel, yourSpeed, yourRange, yourPrice);
	}

	private void removeJetFromFleet() {
		// menu to choose which jet
		// valid menu choice
		// call the airfield method to remove jet
	}

	private void quit() {
		System.out.println("Good Bye!");

		// optional ask if you want to save changed jets collection
	}

}
