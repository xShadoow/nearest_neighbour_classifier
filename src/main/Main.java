package main;

import main.classifier.*;
import main.gui.ClassifierGui;

public class Main {
    public static void main(String[] args) {
        new Main(args);
    }

    public Main(String[] args) {

        ProgramArgs programArgs = readProgramArgs(args);
        //programArgs = new ProgramArgs(new EuclideanClassifier(), 70_000, 28*28, "C://Users//sandr//Downloads//mnist.csv");

        if(programArgs == null) {
            return;
        }

        System.out.println("Loading CSV file...");
        final CSVEntry[] csvEntries = CSVFileReader.readCSVFile(programArgs);
        System.out.println("Opening GUI...");
        new ClassifierGui(csvEntries, programArgs);

        /**
        long startTime = System.currentTimeMillis();
        Classifier classifier = new Classifier(csvEntries, programArgs.getClassifier());
        classifier.runClassifier();
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
        **/
    }

    public ProgramArgs readProgramArgs(String[] args) {

        if(args.length != 5) {
            printHelp();
            return null;
        }

        int n;
        int d;
        String filename;
        int k;
        DistanceType distanceType;

        try {

            n = Integer.parseInt(args[0]); //number of data points to read
            d = Integer.parseInt(args[1]); //dimensions of data points
            filename = args[2]; //filename
            k = Integer.parseInt(args[3]);
            distanceType = (args[4].equalsIgnoreCase("manhattan")) ? DistanceType.MANHATTAN : DistanceType.EUCLIDEAN;

        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Error while reading program arguments! Check inputs please.");
            return null;
        }

        IClassifier classifier;

        if(distanceType == DistanceType.MANHATTAN) {
            if(k == 1) {
                classifier = new ManhattanClassifier();
            } else {
                classifier = new KMajorityManhattanClassifier(k);
            }
        } else {
            if(k == 1) {
                classifier = new EuclideanClassifier();
            } else {
                classifier = new KMajorityEuclideanClassifier(k);
            }
        }

        return new ProgramArgs(classifier, n, d, filename);
    }

    public void printHelp() {
        System.out.println("Warning: Only use this with the mnist dataset! (n should be 40000-70000, d should be 784)");
        System.out.println("Usage: <n> <d> <filename.csv> <k> <distance-metric>");
        System.out.println("<n> number of data points to be read");
        System.out.println("<d> dimensionality of data points");
        System.out.println("<filename.csv> contains the data to be classified (1st line will be ignored)");
        System.out.println("<k> number of neighbours for majority voting");
        System.out.println("<distance-metric>: must be \"manhattan\" or \"euclidean\"");
        System.out.println("Note: This jar opens a gui in which you can draw your own numbers for the mnist dataset to get classified");
    }

}