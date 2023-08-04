package com.example.arcs.tests;

public class OrthyTooth {
	String name;
	double MD;
	double BL;
	double rotation;
	double intrusion;
	double extrusion;  // Added extrusion field
	double tip;
	double torque;

	OrthyTooth(String name, double MD, double BL, double rotation,
	           double intrusion, double extrusion, double tip, double torque) {  // Modified constructor
		this.name = name;
		this.MD = MD;
		this.BL = BL;
		this.rotation = rotation;
		this.intrusion = intrusion;
		this.extrusion = extrusion;  // Set extrusion value
		this.tip = tip;
		this.torque = torque;
	}

	@Override
	public String toString() {
		String str = String.format("%s\t\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f",  // Updated format string
				name, tip, rotation, torque, BL, intrusion, extrusion, MD);  // Added extrusion to string format
		return str;
	}
}
