package com.example.arcs.memory;

public final class TempObjects {

	private static final TempObjects INSTANCE = new TempObjects();
	private TempObjects() {};
	public static TempObjects getInstance() {
		return INSTANCE;
	}

	private Double calibrationValue_1MM;

	public Double getCalibrationValue_1MM() {
		return calibrationValue_1MM;
	}
	public void setCalibrationValue_1MM(Double calibrationValue_1MM) {
		this.calibrationValue_1MM = calibrationValue_1MM;
	}
}
