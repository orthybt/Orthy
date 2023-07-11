package com.example.arcs.essentials;

import javafx.geometry.Point2D;

import java.text.DecimalFormat;
import java.util.List;

public class Printer {
	public static void print(Object obj){
		System.out.println(obj.toString());
	}
	public static void printStatementLogic(boolean bool){
		if (bool) {
			System.out.println("The above statement is true");
		}
		else {
			System.out.println("The above statement is not true");
		}
	}
	public static void printPointCoordinates(String description, Point2D point) {
		System.out.println(description + " - X: " + point.getX() + ", Y: " + point.getY());
	}
	public static void printDouble(double value, String description) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		System.out.println(description + ": " + decimalFormat.format(value));
	}
	public static void printLine(OrthyLine line){
		System.out.println(line.toString());
	}
	public static void printLineLength(OrthyLine line, Double length){
		System.out.println("The length of the line is: " + length);
	}
	public static void printPoints(List<Point2D> points){
		for(Point2D point : points){
			System.out.println(point.toString());
		}
		System.out.println("The total number of points is: " + points.size());
	}
	public static void printArc(OrthyArc arc){
		print(arc);
	}
}
