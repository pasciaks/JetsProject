package com.skilldistillery.jets.entities;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
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
		List<Jet> cargoJets = new ArrayList<>();
		int count = 0;
		for (Jet jet : fleet) {
			if (jet instanceof CargoCarrier) {
				((CargoCarrier) jet).loadCargo();
				cargoJets.add(jet);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("\nNo cargo jets in the fleet.");
		} else {
			System.out.println("\nTotal cargo jets in the fleet: " + count);
		}

		webView("Cargo Carrier Jets Report", cargoJets, "jet_cargo_carrier_jets_report.html");

	}

	public void dogFight() {
		List<Jet> combatJets = new ArrayList<>();
		int count = 0;
		for (Jet jet : fleet) {
			if (jet instanceof CombatReady) {
				((CombatReady) jet).fight();
				combatJets.add(jet);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("\nNo combat ready jets in the fleet.");
		} else {
			System.out.println("\nTotal combat ready jets in the fleet: " + count);
		}

		webView("Combat Ready Jets Report", combatJets, "jet_combat_ready_jets_report.html");

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
				System.out.println(jet.getMacSpeedInMach() + " Mach Speed\n");

				results.add(cloneJet(jet));
			}
		}

		webView("Fastest Jet Report", results, "jet_fastest_report.html");

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
				System.out.println(jet.getFlightTimeInHours() + " Hours Flight Time\n");

				results.add(cloneJet(jet));
			}
		}

		webView("Longest Range Jet Report", results, "jet_longest_range_report.html");

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

		webView("Jet Fleet Report", results, "jet_fleet_report.html");

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
			Jet removedJet = fleet.remove(index);
			if (removedJet != null) {
				return true;
			}
		}
		return false;
	}

	// Returns List<Jet> using cloned version for external use.
	public List<Jet> loadJetsFromFile(String fileName) {

		List<Jet> results = new ArrayList<>();

		List<String> jetLines = this.readFileIntoListOfStrings(fileName);

		if (jetLines == null || jetLines.isEmpty()) {
			System.err.println("No jets found.");
			return results;
		}

		for (String line : jetLines) {

			String[] jetData = line.split(",");

			if (jetData.length != 5) {
				System.err.println("Error: Jet data not in correct format, must have 5 fields.");
				continue;
			}

			for (int i = 0; i < jetData.length; i++) { // just in case there is extra whitespace
				jetData[i] = jetData[i].trim();
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
			break;
		case "CargoPlane":
			jet = new CargoPlane(model, speed, range, price);
			break;
		case "PassengerJet":
			jet = new PassengerJet(model, speed, range, price);
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
			return null;
		}
	}

	private void webView(String title, List<Jet> reportJets, String fileName) {

		List<String> htmlReportTemplate = readFileIntoListOfStrings("template_REPORT.html");
		List<String> htmlCardTemplate = readFileIntoListOfStrings("template_CARD.html");

		List<String> cards = new ArrayList<>();
		List<String> outputedHtml = new ArrayList<>();

		for (Jet jet : reportJets) {
			for (String line : htmlCardTemplate) {
				String card = "" + line;

				card = card.replace("{{type}}", jet.getClass().getSimpleName());
				card = card.replace("{{model}}", jet.getModel());
				card = card.replace("{{speed}}", Double.toString(jet.getSpeed()));
				card = card.replace("{{range}}", Integer.toString(jet.getRange()));
				card = card.replace("{{price}}", Long.toString(jet.getPrice()));
				card = card.replace("{{getFlightTimeInHours}}", Double.toString(jet.getFlightTimeInHours()));
				card = card.replace("{{getMacSpeedInMach}}", Double.toString(jet.getMacSpeedInMach()));
				cards.add(card);
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String s : cards) {
			sb.append(s);
		}

		String cardLines = sb.toString();

		for (String line : htmlReportTemplate) {
			String reportLine = "" + line;
			reportLine = reportLine.replace("{{title}}", title);
			reportLine = reportLine.replace("{{cards}}", cardLines);
			outputedHtml.add(reportLine);
		}

		writeWebView(fileName, outputedHtml);

		showWebView(fileName);

	}

	private void showWebView(String fileName) {
		// Get the Desktop instance
		Desktop desktop = Desktop.getDesktop();

		// Create a File object for the specified file path
		File file = new File(fileName);

		try {
			// Check if the file exists
			if (!file.exists()) {
				System.out.println("File does not exist");
				return;
			}

			// Open the file in the default web browser
			desktop.browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeWebView(String fileName, List<String> htmlLines) {

		PrintStream ps = null;
		try {
			fileName = fileName.replace("/", System.getProperty("file.separator"));

			FileOutputStream fs = new FileOutputStream(fileName);

			ps = new PrintStream(fs);
			for (String line : htmlLines) {
				ps.println(line);
			}
			ps.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close(); // a close method can also throw an exception
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<String> readFileIntoListOfStrings(String fileName) {
		String line = null;
		boolean hasError = false;
		Error error = null;
		List<String> arrayListOfStrings = new ArrayList<>();
		// NOTE: Auto-closable try-with-resources
		try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			while ((line = in.readLine()) != null) {
				arrayListOfStrings.add(line);
			}
		} catch (FileNotFoundException e) { // Catch file not found exceptions
			System.err.println(e);
			error = new Error("File not found: " + fileName);
			hasError = true;
		} catch (IOException e) { // Catch IO exceptions
			System.err.println(e);
			error = new Error("IO Exception: " + e.getMessage());
			hasError = true;
		} catch (Exception e) { // Catch all other exceptions
			System.err.println(e);
			error = new Error("Exception: " + e.getMessage());
			hasError = true;
		} finally {
			// NOTE: No close() method to call, try-with-resources does it for us
		}

		if (hasError) {
			// consider early return of new empty ArrayList<>();
			System.err.println(error);
			System.err.println(error.getMessage());
		}

		return arrayListOfStrings;
	}

}
