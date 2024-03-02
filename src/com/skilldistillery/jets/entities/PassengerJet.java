package com.skilldistillery.jets.entities;

public class PassengerJet extends Jet {

	public PassengerJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(this.getModel() + " is a Passenger Jet flying at a speed of " + this.getSpeed()
				+ " mph with a range of " + this.getRange() + " miles and a price of $" + this.getPrice() + ".");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passenger Jet [model=");
		builder.append(this.getModel());
		builder.append(", speed=");
		builder.append(this.getSpeed());
		builder.append(", range=");
		builder.append(this.getRange());
		builder.append(", price=");
		builder.append(this.getPrice());
		builder.append("]");
		return builder.toString();
	}

}
