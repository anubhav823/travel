package main;

public class Utils {

	/**
	 * method to call appropriate methods as per weather conditions
	 * 
	 * @param weather
	 * @param orbit1_speed
	 * @param orbit2_speed
	 * @return
	 */
	public String[] switching(String weather, int orbitOneSpeed, int orbitTwoSpeed) {
		String array[] = new String[2];
		switch (weather) {
		case "SUNNY":
			array = getFasterVehicleForSunny(orbitOneSpeed, orbitTwoSpeed);
			break;
		case "WINDY":
			array = getFasterVehicleForWindy(orbitOneSpeed, orbitTwoSpeed);
			break;
		case "RAINY":
			array = getFasterVehicleForRainy(orbitOneSpeed, orbitTwoSpeed);
			break;
		default:
			System.err.println("Invalid inputs");
		}
		return array;

	}

	/**
	 * check for faster vehicle in windy weather
	 * 
	 * @param orbit1_speed
	 * @param orbit2_speed
	 * @return
	 */
	public String[] getFasterVehicleForWindy(int orbitOneSpeed, int orbitTwoSpeed) {
		Bike bike = calculateBikeSpeedInOrbits(Constants.WINDY_ORBIT1_CRATERS, Constants.WINDY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		Car car = calculateCarSpeedInOrbits(Constants.WINDY_ORBIT1_CRATERS, Constants.WINDY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		String[] array = new String[2];
		if (car.getMinTime() < bike.getMinTime()) {
			array = setVehicleNameInArray(car, array);
		} else {
			array = setVehicleNameInArray(bike, array);
		}
		return array;
	}

	/**
	 * check for faster vehicle in sunny weather
	 * 
	 * @param orbit1_speed
	 * @param orbit2_speed
	 * @return
	 */
	public String[] getFasterVehicleForSunny(int orbitOneSpeed, int orbitTwoSpeed) {
		Bike bike = calculateBikeSpeedInOrbits(Constants.SUNNY_ORBIT1_CRATERS, Constants.SUNNY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		Car car = calculateCarSpeedInOrbits(Constants.SUNNY_ORBIT1_CRATERS, Constants.SUNNY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		Tuktuk tuktuk = calculateTukTukSpeedInOrbits(Constants.SUNNY_ORBIT1_CRATERS, Constants.SUNNY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		String[] array = new String[2];
		if (car.getMinTime() < tuktuk.getMinTime() && car.getMinTime() < bike.getMinTime()) {
			array = setVehicleNameInArray(car, array);
		} else if (tuktuk.getMinTime() < bike.getMinTime() && tuktuk.getMinTime() <= car.getMinTime()) {
			array = setVehicleNameInArray(tuktuk, array);
		} else {
			array = setVehicleNameInArray(bike, array);
		}
		return array;
	}

	/**
	 * check for faster vehicle in rainy weather
	 * 
	 * @param orbit1_speed
	 * @param orbit2_speed
	 * @return
	 */
	public String[] getFasterVehicleForRainy(int orbitOneSpeed, int orbitTwoSpeed) {
		Car car = calculateCarSpeedInOrbits(Constants.RAINY_ORBIT1_CRATERS, Constants.RAINY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		Tuktuk tuktuk = calculateTukTukSpeedInOrbits(Constants.RAINY_ORBIT1_CRATERS, Constants.RAINY_ORBIT2_CRATERS,
				orbitOneSpeed, orbitTwoSpeed);
		String[] array = new String[2];
		if (car.getMinTime() < tuktuk.getMinTime()) {
			array = setVehicleNameInArray(car, array);
		} else {
			array = setVehicleNameInArray(tuktuk, array);
		}
		return array;
	}

	/**
	 * Calculating speed of bike in orbits
	 * 
	 * @param cratersInOrbitOne
	 * @param cratersInOrbitTwo
	 * @param orbitOneSpeed
	 * @param orbitTwoSpeed
	 * @return
	 */
	public Bike calculateBikeSpeedInOrbits(double cratersInOrbitOne, double cratersInOrbitTwo, int orbitOneSpeed,
			int orbitTwoSpeed) {
		Bike bike = new Bike();
		double bikeTimeInOrbitOne = timeTakenByVehicleInOrbit(cratersInOrbitOne, orbitOneSpeed, Constants.MAX_BIKE_SPEED,
				Constants.ORBIT1_LENGTH, bike.getTimeTakenToCrossOneCrater());
		double bikeTimeInOrbitTwo = timeTakenByVehicleInOrbit(cratersInOrbitTwo, orbitTwoSpeed, Constants.MAX_BIKE_SPEED,
				Constants.ORBIT2_LENGTH, bike.getTimeTakenToCrossOneCrater());
		bike.setMinTime(Math.min(bikeTimeInOrbitOne, bikeTimeInOrbitTwo));
		bike.setOrbitPath(bikeTimeInOrbitTwo < bikeTimeInOrbitOne ? Constants.ORBIT_2 : Constants.ORBIT_1);
		return bike;

	}

	/**
	 * Calculating speed of car in orbits
	 * 
	 * @param cratersInOrbitOne
	 * @param cratersInOrbitTwo
	 * @param orbitOneSpeed
	 * @param orbitTwoSpeed
	 * @return
	 */
	public Car calculateCarSpeedInOrbits(double cratersInOrbitOne, double cratersInOrbitTwo, int orbitOneSpeed,
			int orbitTwoSpeed) {
		Car car = new Car();
		double carTimeInOrbitOne = timeTakenByVehicleInOrbit(cratersInOrbitOne, orbitOneSpeed, Constants.MAX_CAR_SPEED,
				Constants.ORBIT1_LENGTH, car.getTimeTakenToCrossOneCrater());
		double carTimeInOrbitTwo = timeTakenByVehicleInOrbit(cratersInOrbitTwo, orbitTwoSpeed, Constants.MAX_CAR_SPEED,
				Constants.ORBIT2_LENGTH, car.getTimeTakenToCrossOneCrater());
		car.setMinTime(Math.min(carTimeInOrbitOne, carTimeInOrbitTwo));
		car.setOrbitPath(carTimeInOrbitTwo < carTimeInOrbitOne ? Constants.ORBIT_2 : Constants.ORBIT_1);
		return car;
	}

	/**
	 * Calculating speed of tuktuk in orbits
	 * 
	 * @return
	 */
	public Tuktuk calculateTukTukSpeedInOrbits(double cratersInOrbitOne, double cratersInOrbitTwo, int orbitOneSpeed,
			int orbitTwoSpeed) {
		Tuktuk tuktuk = new Tuktuk();
		double tuktukTimeInOrbitOne = timeTakenByVehicleInOrbit(cratersInOrbitOne, orbitOneSpeed,
				Constants.MAX_TUKTUK_SPEED, Constants.ORBIT1_LENGTH, tuktuk.getTimeTakenToCrossOneCrater());
		double tuktukTimeInOrbitTwo = timeTakenByVehicleInOrbit(cratersInOrbitTwo, orbitTwoSpeed,
				Constants.MAX_TUKTUK_SPEED, Constants.ORBIT2_LENGTH, tuktuk.getTimeTakenToCrossOneCrater());
		tuktuk.setMinTime(Math.min(tuktukTimeInOrbitOne, tuktukTimeInOrbitTwo));
		tuktuk.setOrbitPath(tuktukTimeInOrbitTwo < tuktukTimeInOrbitOne ? Constants.ORBIT_2 : Constants.ORBIT_1);
		return tuktuk;
	}

	/**
	 * Method to calculate time taken by a vehicle in an orbit
	 * 
	 * @param windyOrbit1Craters
	 * @param maxSpeedInOrbit
	 * @param maxVehicleSpeed
	 * @param distance
	 * @return
	 */
	public double timeTakenByVehicleInOrbit(double numberOfCraters, double maxSpeedInOrbit, double maxVehicleSpeed,
			double distance, double timeTakenToCrossOneCrater) {
		double timeTakenInOrbit = (numberOfCraters * timeTakenToCrossOneCrater) / 60;
		double vehicleSpeed = Math.min(maxSpeedInOrbit, maxVehicleSpeed);
		return (distance / vehicleSpeed) + timeTakenInOrbit;
	}

	/**
	 * Setting fields in vehicle object
	 * 
	 * @param vehicle
	 * @param array
	 * @return
	 */
	public String[] setVehicleNameInArray(Vehicle vehicle, String[] array) {
		array[0] = vehicle.getVehicleName();
		array[1] = vehicle.getOrbitPath();
		return array;
	}
}
