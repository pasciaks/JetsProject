package com.skilldistillery.jets.entities;

import com.skilldistillery.jets.util.SafeParse;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fight() {
		System.out.println("Dogfighting " + super.toString());
	}

	@Override
	public void fly() {
		System.out.print("Flying Fighter 🛩 ");
		System.out.println(this.getModel() + " at a speed of " + this.getSpeed() + " mph (" + this.getMacSpeedInMach()
				+ " Mach Speed) with a range of " + this.getRange() + " miles.");
		System.out.println(" Price: $" + SafeParse.formatAmountInDollars(this.getPrice()) + "\tFlight Time: "
				+ this.getFlightTimeInHours());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fighter Jet\n");
		builder.append(super.toString());
		return builder.toString();
	}

}
