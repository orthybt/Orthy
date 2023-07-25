//package com.example.arcs.tests;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class DataExtractor {
//        public static void main(String[] args) {
//            String path = "path_to_your_file.txt";
//            List<Tooth> teeth = readTeethDataFromFile(path);
//
//            // Now you have a list of Tooth objects.
//            for (Tooth tooth : teeth) {
//                System.out.println(tooth.toString());
//            }
//
//        public static List<Tooth> readTeethDataFromFile(String path) {
//            List<Tooth> teethh = new ArrayList<>();
//            try {
//                File file = new File(path);
//                Scanner scanner = new Scanner(file);
//                while (scanner.hasNextLine()) {
//                    String data = scanner.nextLine();
//                    if (!data.trim().isEmpty()) {
//                        Tooth tooth = Tooth.extractData(data);
//                        teeth.add(tooth);
//                    }
//                }
//                scanner.close();
//            } catch (FileNotFoundException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//            return teeth;
//        }
//    }
