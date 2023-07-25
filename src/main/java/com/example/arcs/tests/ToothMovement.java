package com.example.arcs.tests;

import java.util.ArrayList;
import java.util.List;

public class ToothMovement {
	private String primaryMovement;
	private List<String> antagonisticMovements;
	private List<String> synergisticMovements;
	private List<String> inevitableMovements;

	public ToothMovement(String primaryMovement) {
		this.primaryMovement = primaryMovement;
		this.antagonisticMovements = new ArrayList<>();
		this.synergisticMovements = new ArrayList<>();
		this.inevitableMovements = new ArrayList<>();
	}

	public void calculateMovements() {
		switch (primaryMovement) {
			case "buccal":
				// Calculate antagonistic, synergistic and inevitable movements for buccal
				break;
			case "lingual":
				// Calculate antagonistic, synergistic and inevitable movements for lingual
				break;
			case "mesial":
				calculateSynergisticMovements("mesial", "mesial rotation");
				break;
			case "distal":
				// Calculate antagonistic, synergistic and inevitable movements for distal
				break;
			// Add other cases as necessary
		}
	}
	public void calculateSynergisticMovements(String primary, String rotation) {
		// Check if primary and rotation movements occur at the same time
		if (primary.equals("mesial") && rotation.equals("mesial rotation")) {
			// Increase step value for mesial rotation to 1.5 degrees per step
			// Make sure rotation is not less than 50% of the step value
			// If there is an antagonistic movement, reduce the value of the mesial by 10-15%
		}
	}
	public static void printDistalizationMovements(double molar2Movement, double molar1Movement, double premolar2Movement, double premolar1Movement) {
		double stepValueMolar2 = molar2Movement / 5.0;
		double stepValueMolar1 = molar1Movement / 5.0;
		double stepValuePremolar2 = premolar2Movement / 5.0;
		double stepValuePremolar1 = premolar1Movement / 5.0;

		double factorMolar1 = 0.65;
		double factorPremolar2 = 0.8;
		double factorPremolar1 = 0.8;

		for(int i = 0; i < 5; i++) {
			double currentStepMolar2 = stepValueMolar2;
			double currentStepMolar1 = factorMolar1 * currentStepMolar2;
			double currentStepPremolar2 = factorPremolar2 * currentStepMolar1;
			double currentStepPremolar1 = factorPremolar1 * currentStepPremolar2;

			// If any step value is < 0.1, increase the factor for next teeth.
			if (Math.abs(currentStepMolar1) < 0.1) {
				factorMolar1 += 0.1;
				factorPremolar2 += 0.05;
			}

			if (Math.abs(currentStepPremolar2) < 0.1) {
				factorPremolar2 += 0.1;
				factorPremolar1 += 0.05;
			}

			System.out.println("Molar 2: " + currentStepMolar2);
			System.out.println("Molar 1: " + currentStepMolar1);
			System.out.println("Premolar 2: " + currentStepPremolar2);
			System.out.println("Premolar 1: " + currentStepPremolar1);
		}
	}


	// Getters and setters
}
