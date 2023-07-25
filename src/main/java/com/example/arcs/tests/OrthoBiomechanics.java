package com.example.arcs.tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrthoBiomechanics {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Ortho Biomechanics Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(new GridLayout(5, 2));

		// Labels and text fields
		JLabel blLabel = new JLabel("BL Movement (mm):");
		JTextField blField = new JTextField();
		JLabel mdLabel = new JLabel("MD Movement (mm):");
		JTextField mdField = new JTextField();

		// Button
		JButton calculateButton = new JButton("Calculate");
		JLabel resultLabel = new JLabel("Results:");
		JTextArea resultArea = new JTextArea();
		resultArea.setEditable(false);

		calculateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double BL = Double.parseDouble(blField.getText());
				double MD = Double.parseDouble(mdField.getText());

				// Calculate diagonal movement
				double diagonalMovement = Math.sqrt(BL * BL + MD * MD);
				StringBuilder results = new StringBuilder();
				results.append("Diagonal movement: ").append(diagonalMovement).append(" mm\n");

				if (diagonalMovement > 1.25 * BL) {
					results.append("Adjustments needed for BL and MD values.\n");
				} else {
					results.append("No adjustments needed for BL and MD values.\n");
				}

				// Calculate minimum steps
				int steps = 1;
				while (!isValidStep(BL, MD, steps)) {
					steps++;
				}

				results.append("Minimum number of steps required: ").append(steps);
				resultArea.setText(results.toString());
			}
		});

		frame.add(blLabel);
		frame.add(blField);
		frame.add(mdLabel);
		frame.add(mdField);
		frame.add(calculateButton);
		frame.add(resultLabel);
		frame.add(resultArea);

		frame.setVisible(true);
	}

	private static boolean isValidStep(double BL, double MD, int steps) {
		double BLPerStep = BL / steps;
		double MDPerStep = MD / steps;

		double combinedMovement = Math.sqrt(BLPerStep * BLPerStep + MDPerStep * MDPerStep);
		return combinedMovement <= 1.25 * BLPerStep;
	}
}
