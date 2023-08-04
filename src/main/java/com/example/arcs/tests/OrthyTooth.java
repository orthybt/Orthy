package com.example.arcs.tests;

public class OrthyTooth {
	String name;
	double MD;
	double BL;
	double rotation;
	double IE;  // Field for Intrusion/Extrusion
	double tip;
	double torque;

	OrthyTooth(String name, double tip, double rotation, double torque,
	           double BL, double IE, double MD) {
		this.name = name;
		this.tip = tip;
		this.rotation = rotation;
		this.torque = torque;
		this.BL = BL;
		this.IE = IE;
		this.MD = MD;
	}

	@Override
	public String toString() {
		String str = String.format("%s\t\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f\t%+.2f",
				name, tip, rotation, torque, BL, IE, MD);
		return str;
	}
}
