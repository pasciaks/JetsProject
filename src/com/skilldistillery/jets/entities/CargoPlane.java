package com.skilldistillery.jets.entities;

public class CargoPlane extends Jet implements CargoCarrier {

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void loadCargo() {
		System.out.println("Loading " + this.getModel());
	}

	@Override
	public void fly() {
		System.out.println(this.getModel() + " is a Cargo Plane flying at a speed of " + this.getSpeed()
				+ " mph with a range of " + this.getRange() + " miles.");
		System.out.println("   Price: " + this.getPrice() + "\tFlight Time: " + this.getFlightTimeInHours());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cargo Plane\n");
		builder.append(super.toString());
		return builder.toString();
	}

}
