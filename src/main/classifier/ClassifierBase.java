package main.classifier;

import main.CSVEntry;

public abstract class ClassifierBase implements IClassifier {

    @Override
    public int classify(CSVEntry[] trainEntries, double[] testDatapoints) {
        CSVEntry closestEntry = trainEntries[0];
        double closestDistance = distance(trainEntries[0].getDatapoints(), testDatapoints);

        for (CSVEntry trainEntry : trainEntries) {
            double newDistance = distance(trainEntry.getDatapoints(), testDatapoints);
            if (newDistance < closestDistance) {
                closestEntry = trainEntry;
                closestDistance = newDistance;
            }
        }

        return closestEntry.getClassification();
    }

}
