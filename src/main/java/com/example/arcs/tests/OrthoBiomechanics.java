package com.example.arcs.tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrthoBiomechanics {
	private static final double MAX_STEP_VALUE = 0.2;  // Default max step value
	public static void main(String[] args) {
		JFrame frame = new JFrame("Ortho Biomechanics Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 600);
		frame.setLayout(new GridLayout(10, 2));

		// Labels and text fields
		JLabel blLabel = new JLabel("BL Movement (mm):");
		JTextField blField = new JTextField();
		blField.setPreferredSize(new Dimension(100, 25));  // Set preferred size

		JLabel mdLabel = new JLabel("MD Movement (mm):");
		JTextField mdField = new JTextField();
		mdField.setPreferredSize(new Dimension(100, 25));  // Set preferred size

		// Button
		JButton calculateButton = new JButton("Calculate");
		JLabel resultLabel = new JLabel("Results:");
		JTextArea resultArea = new JTextArea(10, 30); // Sets 6 rows and 30
		// columns as an example
		resultArea.setEditable(false);
		resultArea.setWrapStyleWord(true);
		resultArea.setLineWrap(true);

		calculateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double BL = Double.parseDouble(blField.getText());
					double MD = Double.parseDouble(mdField.getText());

					// Calculate diagonal movement
					double diagonalMovement = Math.sqrt(BL * BL + MD * MD);
					StringBuilder results = new StringBuilder();

					// Format the diagonalMovement to 2 decimal places
					results.append("Diagonal movement: ").append(String.format("%.2f", diagonalMovement)).append(" mm\n");

					if (diagonalMovement > 1.25 * BL) {
						results.append("Adjustments needed for BL and MD values.\n");
					} else {
						results.append("No adjustments needed for BL and MD values.\n");
					}

					// Calculate minimum steps
					int steps = 1;
					while (!isValidStep(BL, MD, steps) && steps < 100) {  // Assuming max steps as 100
						steps++;
					}

					if (steps < 100) {
						results.append("Minimum number of steps required: ").append(steps).append("\n");

						double adjustedBL = modifyBL(BL, MD, steps);
						double adjustedMD = modifyMD(BL, MD, steps);

						results.append("Adjusted BL movement for ").append(steps).append(" steps: ").append(String.format("%.2f", adjustedBL)).append(" mm\n");
						results.append("Adjusted MD movement for ").append(steps).append(" steps: ").append(String.format("%.2f", adjustedMD)).append(" mm\n");

					} else {
						results.append("Unable to find a suitable number of steps under the current constraints.\n");
					}

					resultArea.setText(results.toString());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for BL and MD.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});


		frame.add(blLabel);
		frame.add(blField);
		frame.add(mdLabel);     // Add the label
		frame.add(mdField);     // Add the text field
		frame.add(calculateButton);    // Add the button
		frame.add(resultLabel);
		frame.add(new JScrollPane(resultArea));


		frame.setVisible(true);
	}

	public static boolean isValidStep(double BL, double MD, int steps) {
		double BLPerStep = BL / steps;
		double MDPerStep = MD / steps;

		// Ensure neither movement per step exceeds the max allowed
		if (BLPerStep > MAX_STEP_VALUE || MDPerStep > MAX_STEP_VALUE) {
			return false;
		}

		double combinedMovement = Math.sqrt(BLPerStep * BLPerStep + MDPerStep * MDPerStep);

		// Checking the combined movement against the 25% allowance of the primary movement
		double allowance = (BLPerStep > MDPerStep) ? 1.25 * BLPerStep : 1.25 * MDPerStep;

		return combinedMovement <= allowance;
	}
	public static double modifyBL(double BL, double MD, int steps) {
		while (BL > 0 && !isValidStep(BL, MD, steps)) {
			BL -= 0.01;  // decrease by 0.01mm increments
		}
		return BL;
	}
	public static double modifyMD(double BL, double MD, int steps) {
		while (MD > 0 && !isValidStep(BL, MD, steps)) {
			MD -= 0.01;  // decrease by 0.01mm increments
		}
		return MD;
	}
}
