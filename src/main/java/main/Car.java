package main;

public class Car extends Vehicle {
	private static String vehicleName = "CAR";
	private static double timeTakenToCrossOneCrater = 3;
	
	@Override
	public String getVehicleName() {
		return vehicleName;
	}

	public double getTimeTakenToCrossOneCrater() {
		return timeTakenToCrossOneCrater;
	}
}
