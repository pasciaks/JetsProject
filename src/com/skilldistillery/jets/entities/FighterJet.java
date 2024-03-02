package com.skilldistillery.jets.entities;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fight() {
		System.out.println(this.getModel() + " is a Fighter Jet fighting in a dogfight.");
		System.out.println();

	}

	@Override
	public void fly() {

		System.out.println(this.getModel() + " is a Fighter Jet flying at a speed of " + this.getSpeed()
				+ " mph with a range of " + this.getRange() + " miles and a price of $" + this.getPrice() + ".");
		System.out.println();

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fighter Jet - Jet [model=");
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
