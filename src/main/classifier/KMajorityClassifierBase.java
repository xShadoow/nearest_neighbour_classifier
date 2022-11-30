package main.classifier;

import main.CSVEntry;

public abstract class KMajorityClassifierBase implements IClassifier {

    private int kMajority;

    public KMajorityClassifierBase(int kMajority) {
        this.kMajority = kMajority;
    }

    @Override
    public int classify(CSVEntry[] trainEntries, double[] testDatapoints) {

        CSVEntry[] closestDistanceEntries = new CSVEntry[kMajority];
        double[] closestDistances = new double[kMajority];

        for(int i = 0; i < kMajority; i++) {
            closestDistanceEntries[i] = trainEntries[i];
            closestDistances[i] = distance(trainEntries[i].getDatapoints(), testDatapoints);
        }

        for(int i = closestDistanceEntries.length; i < trainEntries.length; i++) {
            double newDistance = distance(trainEntries[i].getDatapoints(), testDatapoints);
            this.checkClosest(closestDistanceEntries, closestDistances, trainEntries[i], newDistance);
        }

        int[] classifications = new int[kMajority];

        for(int i = 0; i < kMajority; i++) {
            classifications[i] = closestDistanceEntries[i].getClassification();
        }

        return getMostPopularValue(classifications, closestDistanceEntries, closestDistances);
    }

    private void checkClosest(CSVEntry[] closestDistanceEntries, double[] closestDistances, CSVEntry newEntry, double newDistance) {

        double highestDistance = 0;
        int highestDistanceIndex = 0;

        //find the highest distance entry index
        for(int i = 0; i < kMajority; i++) {
            if(closestDistances[i] > highestDistance) {
                highestDistance = closestDistances[i];
                highestDistanceIndex = i;
            }
        }

        if(newDistance < highestDistance) {
            closestDistances[highestDistanceIndex] = newDistance;
            closestDistanceEntries[highestDistanceIndex] = newEntry;
        }

    }

    private int getMostPopularValue(int[] array, CSVEntry[] closestDistanceEntries, double[] closestDistances) {
        int count = 1;
        int tempCount = 0;
        int mostPopular = array[0];
        int temp = 0;
        for(int i = 0; i < array.length-1; i++) {
            temp = array[i];
            tempCount = 0;
            for(int j = 0; j < array.length; j++) {
                if(temp == array[j]) {
                    tempCount++;
                }
            }
            if(tempCount > count) {
                mostPopular = temp;
                count = tempCount;
            }
        }

        //no majority found! -> return closest
        if(count == 1) {
            System.out.println("no majority");
            double closestDistance = closestDistances[0];
            CSVEntry closestEntry = closestDistanceEntries[0];
            for(int i = 0; i < closestDistances.length; i++) {
                if(closestDistance > closestDistances[i]) {
                    closestDistance = closestDistances[i];
                    closestEntry = closestDistanceEntries[i];
                }
            }
            mostPopular = closestEntry.getClassification();
        }

        return mostPopular;
    }

}
