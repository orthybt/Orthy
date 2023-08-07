package com.example.arcs.tests;

import java.util.List;

public class OrthyLayer {


	private String name;
	private List<OrthyTooth> teeth;

	public OrthyLayer(String name, List<OrthyTooth> teeth) {
		this.name = name;
		this.teeth = teeth;
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OrthyTooth> getTeeth() {
		return teeth;
	}
	public void setTeeth(List<OrthyTooth> teeth) {
		this.teeth = teeth;
	}
	//toString
	@Override
	public String toString() {
		return "OrthyLayer [name=" + name + ", teeth=" + teeth + "]";
	}
}
