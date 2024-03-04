package com.skilldistillery.jets.entities;

import com.skilldistillery.jets.util.SafeParse;

public class PassengerJet extends Jet {

	public PassengerJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.print("Flying Passenger 🛫 ");
		System.out.println(this.getModel() + " at a speed of " + this.getSpeed() + " mph (" + this.getMacSpeedInMach()
				+ " Mach Speed) with a range of " + this.getRange() + " miles.");
		System.out.println(" Price: $" + SafeParse.formatAmountInDollars(this.getPrice()) + "\tFlight Time: "
				+ this.getFlightTimeInHours());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passenger Jet\n");
		builder.append(super.toString());
		return builder.toString();
	}

}
