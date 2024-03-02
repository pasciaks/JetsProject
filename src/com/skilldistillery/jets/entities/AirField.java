package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirField {

	private List<Jet> fleet;

	/*
	 * if (p instanceof Employee) { Employee e = (Employee) p; e.getTitle(); }
	 */

	public AirField() {
		this.fleet = new ArrayList<>();
	}

	public void listFleet() {
		displayFleet();
	}

	public void loadJetsFromFile() {

		List<String> jetLines = this.readFileIntoListOfStrings("jets.txt");

		if (jetLines == null) {
			System.err.println("No jets found.");
			return;
		}

		for (String line : jetLines) {
			String[] jetData = line.split(",");

			String type = jetData[0];

			String model = jetData[1];
			double speed = Short.parseShort(jetData[2]);
			int range = Integer.parseInt(jetData[3]);
			long price = Long.parseLong(jetData[4]);

			boolean wasAdded = this.addJetToAirField(type, model, speed, range, price);

			if (wasAdded) {
				System.out.println("Jet added: " + model);
			} else {
				System.out.println("Jet not added: " + model);
			}

		}

	}

	public Jet addJet(String type, String model, double speed, int range, long price) {
		Jet jet = null;
		switch (type) {
		case "FighterJet":
			jet = new FighterJet(model, speed, range, price);
			System.out.println("FighterJet"); // debug purposes
			break;
		case "CargoPlane":
			jet = new CargoPlane(model, speed, range, price);
			System.out.println("CargoPlane");
			break;
		case "JetImpl":
			jet = new JetImpl(model, speed, range, price);
			System.out.println("JetImpl");
			break;
		default:
			// Note: Jet is abstract, a default Jet is not possible
			break;
		}
		return jet;
	}

	public boolean addJetToAirField(String type, String model, double speed, int range, long price) {
		Jet newJet = addJet(type, model, speed, range, price);
		if (newJet != null) {
			fleet.add(newJet);
			return true;
		} else {
			return false;
		}
	}

	public void displayFleet() {
		for (Jet jet : fleet) {
			System.out.println(jet);
		}
	}

	private List<String> readFileIntoListOfStrings(String fileName) {
		String line = null;
		List<String> arrayListOfStrings = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			while ((line = in.readLine()) != null) {
				arrayListOfStrings.add(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return arrayListOfStrings;
	}

}
