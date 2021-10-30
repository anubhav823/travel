package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Geektrust {
	public static void main(String[] args) throws Exception {
		File file = new File(args[0]);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String weather;
		Utils utils = new Utils();
		int orbitOneSpeed, orbitTwoSpeed;
		String line;
		String[] choices;
		while ((line = in.readLine()) != null) {
			String[] arr = line.split(" ");
			weather = arr[0];
			orbitOneSpeed = Integer.parseInt(arr[1]);
			orbitTwoSpeed = Integer.parseInt(arr[2]);
			choices = utils.switching(weather, orbitOneSpeed, orbitTwoSpeed);
			System.out.println(choices[0] + " " + choices[1]);
		}
		in.close();
	}
}
