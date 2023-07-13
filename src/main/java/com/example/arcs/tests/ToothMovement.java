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

	// Getters and setters
}
