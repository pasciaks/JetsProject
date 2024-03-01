package com.skilldistillery.jets.app;

import com.skilldistillery.jets.entities.AirField;

public class JetsApplication {

	private AirField airField;

	public static void main(String[] args) {

		JetsApplication app = new JetsApplication();
		app.launch();
	}

	private void launch() {

		airField = new AirField();
		airField.loadJets();
		airField.displayFleet();

	}

}
