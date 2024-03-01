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

	private void saveJets() {

		// optional

	}

	public void listFleet() {
		displayFleet();
	}

	public void loadJets() {

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

			Jet jet = this.addJet(type, model, speed, range, price);

			if (jet != null) {
				fleet.add(jet);
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
