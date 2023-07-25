package com.example.arcs.tests;

public class Tooth {
    private int number;
    private double tip;
    private double rotation;
    private double torque;
    private double BL;
    private double IntExt;
    private double MD;

    public Tooth(int number, double tip, double rotation, double torque, double BL, double IntExt, double MD) {
        this.number = number;
        this.tip = tip;
        this.rotation = rotation;
        this.torque = torque;
        this.BL = BL;
        this.IntExt = IntExt;
        this.MD = MD;
    }

    // getters and setters omitted for brevity

    public static Tooth extractData(String input) {
        String[] data = input.split("\t");
        return new Tooth(
                Integer.parseInt(data[1]),
                Double.parseDouble(data[2]),
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4]),
                Double.parseDouble(data[5]),
                Double.parseDouble(data[6]),
                Double.parseDouble(data[7])
        );
    }
}
