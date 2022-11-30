package main.classifier;

import main.CSVEntry;

public interface IClassifier {

    abstract int classify(CSVEntry[] trainEntries, double[] testDatapoints);
    abstract double distance(double[] trainDatapoints, double[] testDatapoints);

}
