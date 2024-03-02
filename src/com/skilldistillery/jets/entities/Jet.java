package com.skilldistillery.jets.entities;

import java.util.Objects;

public abstract class Jet {

	private String model;
	private double speed;
	private int range;
	private long price;

	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}

	public abstract void fly();

	@Override
	public int hashCode() {
		return Objects.hash(model, price, range, speed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jet other = (Jet) obj;
		return Objects.equals(model, other.model) && price == other.price && range == other.range
				&& Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("              Model:   " + this.getModel() + "\n");
		builder.append("        Speed (MPH):   " + this.getSpeed() + "\n");
		builder.append("      Range (MILES):   " + this.getRange() + "\n");
		builder.append("              Price: $ " + this.getPrice() + "\n");
		// builder.append(" Mac Speed (Mach): ");
		// builder.append(this.getMacSpeedInMach());
		// builder.append("\n");
		// builder.append("Flight Time (Hours): ");
		// builder.append(this.getFlightTimeInHours());
		// builder.append("\n");
		return builder.toString();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public double getMacSpeedInMach() {
		// = Speed of Sound (at given altitude) MPH / Speed (MPH)
		return this.getSpeed() / 761.2;
	}

	public double getFlightTimeInHours() {
		// = Range (Miles) / Speed (MPH)
		return this.getRange() / this.getSpeed();
	}

}
