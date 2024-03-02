package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirField {

	private List<Jet> fleet;

	public AirField() {
		this.fleet = new ArrayList<>();
	}

	public void flyAllJets() {
		for (Jet jet : fleet) {
			jet.fly();
		}
		if (fleet.isEmpty()) {
			System.out.println("\nNo jets in the fleet.");
		} else {
			int count = this.fleet.size();
			System.out.println("\nTotal jets in the fleet: " + count);
		}
	}

	public void loadAllCargoJets() {
		int count = 0;
		for (Jet jet : fleet) {
			if (jet instanceof CargoCarrier) {
				((CargoCarrier) jet).loadCargo();
				count++;
			}
		}
		if (count == 0) {
			System.out.println("\nNo cargo jets in the fleet.");
		} else {
			System.out.println("\nTotal cargo jets in the fleet: " + count);
		}
	}

	public void dogFight() {
		int count = 0;
		for (Jet jet : fleet) {
			if (jet instanceof CombatReady) {
				((CombatReady) jet).fight();
				count++;
			}
		}
		if (count == 0) {
			System.out.println("\nNo combat ready jets in the fleet.");
		} else {
			System.out.println("\nTotal combat ready jets in the fleet: " + count);
		}
	}

	public void viewFastestJet() {

		double fastestSpeed = 0; // speed should be always positive and non-zero

		for (Jet jet : fleet) {
			if (jet.getSpeed() > fastestSpeed) {
				fastestSpeed = jet.getSpeed();
			}
		}

		System.out.println("Jet(s) with fastest speed: " + fastestSpeed + " mph \n");

		// Note: It's possible there is more than 1 jet with the same fastest speed
		for (Jet jet : fleet) {
			if (jet.getSpeed() >= fastestSpeed) {
				System.out.println(jet);
			}
		}

	}

	public void viewJetWithLongestRange() {

		int longestRange = 0; // range should be always positive and non-zero

		for (Jet jet : fleet) {
			if (jet.getRange() > longestRange) {
				longestRange = jet.getRange();
			}
		}

		System.out.println("Jet(s) with Longest Range: " + longestRange + " miles \n");

		for (Jet jet : fleet) {
			if (jet.getRange() >= longestRange) {
				System.out.println(jet);
			}
		}

	}

	public void listFleet() {
		int count = 0;
		for (Jet jet : fleet) {
			System.out.println(jet);
			count++;
		}
		if (count == 0) {
			System.out.println("\nNo jets in the fleet.");
		} else {
			System.out.println("\nTotal jets in the fleet: " + count);
		}
	}

	public List<String> getFleetList() {
		List<String> fleetList = new ArrayList<>();
		for (Jet jet : fleet) {
			fleetList.add(jet.toString());
		}
		return fleetList;
	}

	public boolean removeJetFromFleet(int index) {
		if (index >= 0 && index < fleet.size()) {
			fleet.remove(index);
			return true;
		}
		return false;
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
				// System.out.println("Jet added: " + model);
			} else {
				// System.out.println("Jet not added: " + model);
			}

		}

	}

	public Jet addJet(String type, String model, double speed, int range, long price) {
		Jet jet = null;
		switch (type) {
		case "FighterJet":
			jet = new FighterJet(model, speed, range, price);
			// System.out.println("FighterJet"); // debug purposes
			break;
		case "CargoPlane":
			jet = new CargoPlane(model, speed, range, price);
			// System.out.println("CargoPlane");
			break;
		case "PassengerJet":
			jet = new PassengerJet(model, speed, range, price);
			// System.out.println("PassengerJet");
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
