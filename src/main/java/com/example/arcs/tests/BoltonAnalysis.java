package com.example.arcs.tests;

public class BoltonAnalysis {

	private double TOTAL_BOLTON_RATIO = 91.3;
	private double ANTERIOR_BOLTON_RATIO = 77.2;
	private double totalRatio;
	private double anteriorRatio;
	private double standardDeviation = 2;
	private double maxTotalExcess;
	private double mandTotalExcess;
	private double maxAntExcess;
	private double mandAntExcess;
	private boolean isMaxTotalExcess;
	private boolean isMandTotalExcess;
	private boolean isMaxAntExcess;
	private boolean isMandAntExcess;

	public void calculateTotalBolton( double maxSum, double mandSum){
		totalRatio = (mandSum / maxSum) * 100;
		maxTotalExcess = (maxSum - mandSum) / TOTAL_BOLTON_RATIO * 100;
		mandTotalExcess = (mandSum - maxSum) / TOTAL_BOLTON_RATIO * 100;
		if (totalRatio < TOTAL_BOLTON_RATIO){
			isMaxTotalExcess = true;
		}
		else if (totalRatio > TOTAL_BOLTON_RATIO){
			isMandTotalExcess = true;
		}
		else {
			isMaxTotalExcess = false;
			isMandTotalExcess = false;
		}
	}
	public void calculateAnteriorBolton( double maxAntSum, double mandAntSum){
		anteriorRatio = (mandAntSum / maxAntSum) * 100;
		maxAntExcess = (maxAntSum - mandAntSum) / ANTERIOR_BOLTON_RATIO * 100;
		mandAntExcess = (mandAntSum - maxAntSum) / ANTERIOR_BOLTON_RATIO * 100;
		if (anteriorRatio < ANTERIOR_BOLTON_RATIO){
			isMaxAntExcess = true;
		}
		else if (anteriorRatio > ANTERIOR_BOLTON_RATIO){
			isMandAntExcess = true;
		}
		else {
			isMaxAntExcess = false;
			isMandTotalExcess = false;
		}
	}

	public String analyzeTotalBolton(){
		String result = "";
		if (isMaxTotalExcess){
			result = String.format(
					"Maxillary excess: %.1f mm\n" +
							"Bolton ratio: %.1f",
					maxTotalExcess, totalRatio);
		}
		else if (isMandTotalExcess){
			result = String.format(
					"Mandibular excess: %.1f mm\n" +
							"Bolton ratio: %.1f",
					mandTotalExcess, totalRatio);
		}
		else {
			result = "Total Bolton ratio is within normal limits";
		}
		return result;
	}
	public String analyzeAnteriorBolton(){
		String result = "";
		if (isMaxAntExcess){
			result = String.format(
					"Maxillary anterior excess: %.1f mm\n" +
							"Anterior Bolton ratio: %.1f",
					maxAntExcess, anteriorRatio);
		}
		else if (isMandAntExcess){
			result = String.format(
					"Mandibular anterior excess: %.1f mm\n" +
							"Bolton ratio: %.1f",
					mandAntExcess, anteriorRatio);
		}
		else {
			result = "Anterior Bolton ratio is within normal limits";
		}
		return result;
	}

	// Getters and setters


}
