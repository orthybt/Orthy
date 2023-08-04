package com.example.arcs.tests;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataExtractor {

	public static List<OrthyTooth> extractData(String filePath) throws IOException {
		List<OrthyTooth> teeth = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (line.startsWith("Layer") || line.trim().isEmpty()) {
					continue;  // Skip the layer and blank lines
				}
				String[] values = line.split(",");
				if (values.length < 7) {
					//System.out.println("Skipping line " + lineNumber);
					continue;
				}
				try {
					// Adjusted the OrthyTooth object creation to match the new constructor
					teeth.add(new OrthyTooth(values[0].trim(), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6])));
				} catch (NumberFormatException e) {
					//System.out.println("Skipping line " + lineNumber + " due to non-numeric values.");
				}
			}
		}
		return teeth;
	}
//	public static void compareCSVs(String filePath1, String filePath2) throws IOException {
//		List<OrthyTooth> teeth1 = extractData(filePath1);
//		List<OrthyTooth> teeth2 = extractData(filePath2);
//
//		if (teeth1.size() != teeth2.size()) {
//			System.out.println("The two CSV files have different numbers of teeth entries.");
//			return;
//		}
//
//		for (int i = 0; i < teeth1.size(); i++) {
//			OrthyTooth tooth1 = teeth1.get(i);
//			OrthyTooth tooth2 = teeth2.get(i);
//
//			if (!tooth1.name.equals(tooth2.name)) {
//				System.out.println("Mismatch in tooth names at position " + (i + 1));
//				continue;
//			}
//
//			if (tooth1.MD != tooth2.MD) {
//				System.out.println("Mismatch in MD value for tooth " + tooth1.name);
//			}
//			if (tooth1.BL != tooth2.BL) {
//				System.out.println("Mismatch in BL value for tooth " + tooth1.name);
//			}
//			if (tooth1.rotation != tooth2.rotation) {
//				System.out.println("Mismatch in rotation value for tooth " + tooth1.name);
//			}
//			if (tooth1.IE != tooth2.IE) {
//				System.out.println("Mismatch in intrusion value for tooth " + tooth1.name);
//			}
//			if (tooth1.IE != tooth2.extrusion) {
//				System.out.println("Mismatch in extrusion value for tooth " + tooth1.name);
//			}
//			if (tooth1.tip != tooth2.tip) {
//				System.out.println("Mismatch in tip value for tooth " + tooth1.name);
//			}
//			if (tooth1.torque != tooth2.torque) {
//				System.out.println("Mismatch in torque value for tooth " + tooth1.name);
//			}
//		}
//	}
	public static void main(String[] args) throws IOException {


		String selectedFilePath = "C:\\Users\\User\\Documents\\DataCompare\\outputCSV_MAX.csv";
		List<OrthyTooth> teeth = extractData(selectedFilePath);

		// Initialize layer counter
		int layerNumber = 1;
		String valueHeader = String.format("%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t" +
						"\t%s",
				"Tooth", "TIP", "Rot", "Tor", "BL", "IE", "MD");
		// Print the first layer heading
		System.out.println("=== Layer " + layerNumber + " ===");
		System.out.println(valueHeader);


		for (OrthyTooth tooth : teeth) {
			// If the current tooth name is "18", check if it's the start of a new layer
			if ("18".equals(tooth.name)) {
				if (layerNumber > 1) {  // Skip for the first layer
					System.out.println("\n=== Layer " + layerNumber + " ===");
					System.out.println(valueHeader);
				}
				layerNumber++;
			}
			System.out.println(tooth);
		}
//		String filePath1 = "C:\\Users\\User\\Documents\\DataCompare\\outputCSV_MAX.csv";
//		String filePath2 = "C:\\Users\\User\\Documents\\DataCompare" +
//				"\\outputCSV_MAX_TestCompare.csv";
//		compareCSVs(filePath1, filePath2);
	}
}
