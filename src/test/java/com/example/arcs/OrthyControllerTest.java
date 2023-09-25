package com.example.arcs;

import com.example.arcs.tests.BoltonAnalysis;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrthyControllerTest {

	@Test
	public void testAdd() {
		BoltonAnalysis calculator = new BoltonAnalysis();
		//first run the method that initializes the sum -the method that you are testing
		calculator.calculateTotalBolton(50, 50);
		//the run a test method so you can analyze the returned value of the
		// method that you are testing
		double sum = calculator.getTotalRatio();
		System.out.println("sum = " + sum);
	}
}
