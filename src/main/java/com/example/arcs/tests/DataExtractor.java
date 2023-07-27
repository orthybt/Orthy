package com.example.arcs.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class OrthyTooth {
	String name;
	double MD;
	double BL;
	double rotation;
	double intrusion;
	double tip;
	double torque;

	OrthyTooth(String name, double MD, double BL, double rotation,
	           double intrusion, double tip, double torque) {
		this.name = name;
		this.MD = MD;
		this.BL = BL;
		this.rotation = rotation;
		this.intrusion = intrusion;
		this.tip = tip;
		this.torque = torque;

	}

	@Override
	public String toString() {
		String str = String.format("%s\t\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f",
				name, tip, rotation, torque, BL, intrusion, MD);
		return str;
	}





}

public class DataExtractor {

	public static List<OrthyTooth> extractData(String filePath) throws IOException {
		List<OrthyTooth> teeth = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			br.readLine(); // skip header
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				teeth.add(new OrthyTooth(values[0].trim(),
						Double.parseDouble(values[1]),
						Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6])));
			}
		}
		return teeth;
	}

	public static void main(String[] args) throws IOException {
		List<OrthyTooth> teeth = extractData("C:\\Users\\User\\Desktop\\Book1.csv");
		System.out.println("Name\tTip\t\tRot\t\tTo\t\tBL\t\tI_E\t\tMD");
		for (OrthyTooth orthyTooth : teeth) {
			System.out.println(orthyTooth);
		}
	}
}
