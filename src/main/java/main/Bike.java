package main;

public class Bike extends Vehicle {
	private static String vehicleName = "BIKE";
	private static double timeTakenToCrossOneCrater = 2;

	@Override
	public String getVehicleName() {
		return vehicleName;
	}

	public double getTimeTakenToCrossOneCrater() {
		return timeTakenToCrossOneCrater;
	}
}
