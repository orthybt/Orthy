package com.example.arcs.tests;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class DentalDataTransformer {

	public static void main(String[] args) {
		// Using JFileChooser to select the input file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select the 'Dino - Dental model analysis report.csv' file");
		int result = fileChooser.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "No file selected. Exiting.");
			return;
		}
		String inputFilePath = fileChooser.getSelectedFile().getAbsolutePath();

		// Using JFileChooser to select the output file location
		fileChooser.setDialogTitle("Save the transformed file as");
		result = fileChooser.showSaveDialog(null);
		if (result != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "No save location selected. Exiting.");
			return;
		}
		String outputFilePath = fileChooser.getSelectedFile().getAbsolutePath();

		transformDentalData(inputFilePath, outputFilePath);
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
}
