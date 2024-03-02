package com.skilldistillery.jets.entities;

public class PassengerJet extends Jet {

	public PassengerJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(this.getModel() + " is a Passenger Jet flying at a speed of " + this.getSpeed()
				+ " mph with a range of " + this.getRange() + " miles.");
		System.out.println("   Price: " + this.getPrice() + "\tFlight Time: " + this.getFlightTimeInHours());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passenger Jet\n");
		builder.append(super.toString());
		return builder.toString();
	}

}
