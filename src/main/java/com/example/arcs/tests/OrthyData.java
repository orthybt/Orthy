package com.example.arcs.tests;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This is going to be a wrapper class. It will contain the static methods
 * from DentalDataTransformer and DataExtractor
 *
 */
public class OrthyData {

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
					teeth.add(new OrthyTooth(values[0].trim(), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6])));  // Setting extrusion to 0.0 as it's not present in the CSV
				} catch (NumberFormatException e) {
					//System.out.println("Skipping line " + lineNumber + "
					// due to non-numeric values.");
				}
			}
		}
		return teeth;
	}
	public static void writeTeethToCSV(List<OrthyTooth> teeth, String outputPath) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
			// Writing the headers
			bw.write("Teeth,TIP,Rotation,Torque,Buccal-Lingual,Extrusion/Intrusion,Mesial-Distal\n");

			// Writing each tooth data
			for (OrthyTooth tooth : teeth) {
				bw.write(String.format("%s,%f,%f,%f,%f,%f,%f\n",
						tooth.name, tooth.tip, tooth.rotation, tooth.torque,
						tooth.BL, tooth.IE, tooth.MD));
			}
		}
	}
	public static void transformDentalData(String inputFilePath, String outputFilePath) {
		try (CSVReader reader = new CSVReader(new FileReader(inputFilePath));
		     CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {

			List<String[]> allElements = reader.readAll();
			List<String[]> transformedData = new ArrayList<>();

			// Start with an empty row
			transformedData.add(new String[]{null, null, null, null, null, null, null});
			// Add the "Layer1" subheading
			transformedData.add(new String[]{"Layer1", null, null, null, null, null, null});

			// Skip metadata and add the actual data
			for (int i = 20; i < allElements.size(); i++) {
				String[] row = allElements.get(i);
				if (row[0] != null && !row[0].contains("IPR")) {
					transformedData.add(row);
				}
			}

			writer.writeAll(transformedData);
			JOptionPane.showMessageDialog(null, "Data transformation complete. Saved to: " + outputFilePath);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "IO Error: " + e.getMessage());
		} catch (CsvException e) {
			JOptionPane.showMessageDialog(null, "Error processing CSV: " + e.getMessage());
		}
	}

	public static String getSelectedFilePath() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
	}
	public static void printTeeth(List<OrthyTooth> teeth) throws IOException {
		String selectedFilePath = "C:\\Users\\User\\Documents\\DataCompare\\outputCSV_MAX.csv";
		List<OrthyTooth> teethTemp = extractData(selectedFilePath);

		// Initialize layer counter
		int layerNumber = 1;
		String valueHeader = String.format("%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t" +
						"\t%s",
				"Tooth", "TIP", "Rot", "Tor", "BL", "IE", "MD");
		// Print the first layer heading
		System.out.println("=== Layer " + layerNumber + " ===");
		System.out.println(valueHeader);


		for (OrthyTooth tooth : teethTemp) {
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

	}

	public static void main(String args[]) throws IOException {
//		System.out.println(getSelectedFilePath());
//		writeTeethToCSV(extractData(getSelectedFilePath()), getSelectedFilePath());
//		printTeeth(extractData(getSelectedFilePath()));
	}
}
