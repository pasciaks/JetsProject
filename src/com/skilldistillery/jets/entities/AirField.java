package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.jets.util.SafeParse;

public class AirField {

	// NOTE: List is the suitable type because an airfield can have multiple jets
	// of the same kind, there are no unique identifiers for each jet
	// and the order of the jets is important.

	/*
	 * In Java, the ArrayList class guarantees the order of elements. When you add
	 * elements to an ArrayList, they are stored in the order in which they were
	 * inserted. Additionally, when you iterate over an ArrayList, the elements are
	 * returned in the same order as they were added.
	 * 
	 * This order preservation is one of the key characteristics of ArrayList and is
	 * defined in the Java documentation. Therefore, you can rely on the order of
	 * elements in an ArrayList to remain consistent unless you explicitly modify it
	 * by adding, removing, or reordering elements.
	 */

	private List<Jet> fleet;

	public AirField() {
		this.fleet = new ArrayList<>();
	}

	public void flyAllJets() {

		if (fleet.isEmpty()) {
			System.out.println("\nNo jets in the fleet.");
			return;
		}

		for (Jet jet : fleet) {
			jet.fly();
		}

		System.out.println("Total jets in the fleet: " + this.fleet.size());

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

	// Returns List<Jet> using cloned version for external use.
	public List<Jet> viewFastestJet() {

		List<Jet> results = new ArrayList<>();

		if (fleet.isEmpty()) {
			System.out.println("\nNo jets in the fleet.");
			return results;
		}

		double fastestSpeed = 0; // any or all will be faster than 0 mph

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
				results.add(cloneJet(jet));
			}
		}

		return results;

	}

	// Returns List<Jet> using cloned version for external use.
	public List<Jet> viewJetWithLongestRange() {

		List<Jet> results = new ArrayList<>();

		if (fleet.isEmpty()) {
			System.out.println("\nNo jets in the fleet.");
			return results;
		}

		int longestRange = 0; // any or all will be longer than 0 miles

		for (Jet jet : fleet) {
			if (jet.getRange() > longestRange) {
				longestRange = jet.getRange();
			}
		}

		System.out.println("Jet(s) with Longest Range: " + longestRange + " miles \n");

		for (Jet jet : fleet) {
			if (jet.getRange() >= longestRange) {
				System.out.println(jet);
				results.add(cloneJet(jet));
			}
		}

		return results;

	}

	// Returns List<Jet> using cloned version for external use.
	public List<Jet> listFleet() {

		List<Jet> results = new ArrayList<>();

		if (fleet.isEmpty()) {
			System.out.println("\nNo jets in the fleet.");
			return results;
		}

		int count = 0;
		for (Jet jet : fleet) {
			System.out.println(jet);
			results.add(cloneJet(jet));
			count++;
		}

		System.out.println("\nTotal jets in the fleet: " + count);

		return results;
	}

	public List<String> getFleetStringList() {
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

	// Returns List<Jet> using cloned version for external use.
	public List<Jet> loadJetsFromFile(String fileName) {

		List<Jet> results = new ArrayList<>();

		List<String> jetLines = this.readFileIntoListOfStrings(fileName);

		if (jetLines == null) {
			System.err.println("No jets found.");
			return results;
		}

		for (String line : jetLines) {

			String[] jetData = line.split(",");

			if (jetData.length != 5) {
				System.err.println("Error: Jet data not in correct format, must have 5 fields.");
				continue;
			}

			String type = jetData[0];

			String model = jetData[1];

			double speed = SafeParse.parseDouble(jetData[2]).getStatusCode() != 500
					? SafeParse.parseDouble(jetData[2]).getDoubleValue()
					: 0.0;
			int range = SafeParse.parseInt(jetData[3]).getStatusCode() != 500
					? SafeParse.parseInt(jetData[3]).getIntValue()
					: 0;
			long price = SafeParse.parseLong(jetData[4]).getStatusCode() != 500
					? SafeParse.parseLong(jetData[4]).getLongValue()
					: 0;

			// NOTE: Adds to fleet, but returns a cloned Jet for this external use
			Jet addedJet = this.addJetToAirField(type, model, speed, range, price);

			if (addedJet != null) {

				// DONE: Adding a cloned Jet to protect Reference Data

				Jet clonedJet = cloneJet(addedJet);

				results.add(clonedJet); // DONE: Adding a cloned Jet to protect Reference Data

				System.out.println("Jet added to fleet: " + clonedJet.getModel());
			}

		}

		return results;

	}

	private Jet cloneJet(Jet jet) {
		Jet clonedJet = null;
		if (jet instanceof FighterJet) {
			clonedJet = new FighterJet(jet.getModel(), jet.getSpeed(), jet.getRange(), jet.getPrice());
		} else if (jet instanceof CargoPlane) {
			clonedJet = new CargoPlane(jet.getModel(), jet.getSpeed(), jet.getRange(), jet.getPrice());
		} else if (jet instanceof PassengerJet) {
			clonedJet = new PassengerJet(jet.getModel(), jet.getSpeed(), jet.getRange(), jet.getPrice());
		}
		return clonedJet;
	}

	private Jet createJet(String type, String model, double speed, int range, long price) {
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

	public Jet addJetToAirField(String type, String model, double speed, int range, long price) {
		Jet newJet = createJet(type, model, speed, range, price);
		if (newJet != null) {
			fleet.add(newJet);
			return cloneJet(newJet);
		} else {
			return cloneJet(newJet);
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
