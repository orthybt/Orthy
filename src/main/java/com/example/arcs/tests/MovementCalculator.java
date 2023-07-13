package com.example.arcs.tests;

import java.text.DecimalFormat;

public class MovementCalculator {
	private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

	public static double area(double base, double height) {
		return (base * height) / 2.0;
	}

	public static double newHeight(double newBase, double area) {
		return (2.0 * area) / newBase;
	}

	public static void main(String[] args) {
		double baseArea = area(0.2, 0.05);
		System.out.println("The area of the base triangle is: " + decimalFormat.format(baseArea));

		double newBase = 0.1;
		double newHeight = newHeight(newBase, baseArea);
		System.out.println("The height for the new base " + decimalFormat.format(newBase) +
				" to maintain the area is: " + decimalFormat.format(newHeight));
	}
}
