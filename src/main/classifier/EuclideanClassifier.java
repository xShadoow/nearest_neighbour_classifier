package main.classifier;

import main.CSVEntry;

public class EuclideanClassifier extends ClassifierBase {

    @Override
    public int classify(CSVEntry[] trainEntries, double[] testDatapoints) {
        return super.classify(trainEntries, testDatapoints);
    }

    @Override
    public double distance(double[] trainDatapoints, double[] testDatapoints) {
        double distance = 0.0D;
        for(int i = 0; i < trainDatapoints.length; i++) {
            distance += Math.pow(trainDatapoints[i] - testDatapoints[i], 2);
        }
        return distance;
    }

}
