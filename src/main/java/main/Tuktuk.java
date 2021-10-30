package main;

public class Tuktuk extends Vehicle {
	private static String vehicleName = "TUKTUK";
	private static double timeTakenToCrossOneCrater = 1;

	@Override
	public String getVehicleName() {
		return vehicleName;
	}

	public double getTimeTakenToCrossOneCrater() {
		return timeTakenToCrossOneCrater;
	}
}
