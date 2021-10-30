package main;

public class Vehicle {
	private String orbitPath;
	private String vehicleName;
	private double minTime;

	public double getMinTime() {
		return minTime;
	}

	public void setMinTime(double minTime) {
		this.minTime = minTime;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getOrbitPath() {
		return orbitPath;
	}

	public void setOrbitPath(String orbitPath) {
		this.orbitPath = orbitPath;
	}

	public String getVehicleName() {
		return vehicleName;
	}
}
