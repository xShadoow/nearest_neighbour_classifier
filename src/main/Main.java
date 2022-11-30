package main;

import main.classifier.EuclideanClassifier;
import main.classifier.KMajorityEuclideanClassifier;
import main.classifier.KMajorityManhattanClassifier;
import main.classifier.ManhattanClassifier;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        //String filePath = "C://Users//sandr//Downloads//agk-ue08-p1.csv"; //dim: 2
        //String filePath = "C://Users//sandr//Downloads//iris.csv"; //dim 4:
        String filePath = "C://Users//sandr//Downloads//mnist.csv"; //dim: 784
        final CSVEntry[] csvEntries = CSVFileReader.readCSVFile(filePath, 10240, 784);

        long startTime = System.currentTimeMillis();
        Classifier euclideanClassifier = new Classifier(csvEntries, new ManhattanClassifier());
        euclideanClassifier.runClassifier();
        long endTime = System.currentTimeMillis();
        System.out.println("Took: " + (endTime - startTime) + "ms");

        /**
        startTime = System.currentTimeMillis();
        Classifier manhattanClassifier = new Classifier(csvEntries, new EuclideanClassifier());
        manhattanClassifier.runClassifier();
        endTime = System.currentTimeMillis();
        System.out.println("Took: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        Classifier kMajorityManhattanClassifier = new Classifier(csvEntries, new KMajorityManhattanClassifier(1));
        kMajorityManhattanClassifier.runClassifier();
        endTime = System.currentTimeMillis();
        System.out.println("Took: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        Classifier kMajorityEuclideanClassifier = new Classifier(csvEntries, new KMajorityEuclideanClassifier(1));
        kMajorityEuclideanClassifier.runClassifier();
        endTime = System.currentTimeMillis();
        System.out.println("Took: " + (endTime - startTime) + "ms");
        **/
    }

}