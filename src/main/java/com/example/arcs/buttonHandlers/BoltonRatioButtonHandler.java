package com.example.arcs.buttonHandlers;

import com.example.arcs.tests.BoltonAnalysis;
import javafx.scene.control.TextArea;


public class BoltonRatioButtonHandler {
	private BoltonAnalysis boltonAnalysis;
	private TextArea textArea;

	public BoltonRatioButtonHandler(TextArea textArea) {
		this.textArea = textArea;
	}

	public void handle(BoltonAnalysis boltonAnalysis) {
		String result = String.format("%s\n",
				boltonAnalysis.analyzeTotalBolton());
		//TBA reconstruct so anteriors and totals can be analyzed separately
				//				boltonAnalysis.analyzeAnteriorBolton());
		textArea.setText(result);
	}
}
