package main;

import main.classifier.IClassifier;

public class Classifier {

    private final CSVEntry[] allEntries;
    private final IClassifier classifier;

    public Classifier(final CSVEntry[] allEntries, IClassifier classifier) {
        this.allEntries = allEntries;
        this.classifier = classifier;
    }

    public int runClassifier() {

        System.out.println("Classifying...");

        int correctlyClassified = 0;
        final CSVEntry[] trainEntries = new CSVEntry[this.allEntries.length-1];
        CSVEntry testEntry;
        int progressModulo = this.allEntries.length/20;
        int progress = 0;

        for(int i = 0; i < this.allEntries.length; i++) {

            testEntry = this.allEntries[i];
            writeTrainEntries(trainEntries, i);

            int classifiedAs = classifier.classify(trainEntries, testEntry.getDatapoints());

            if(classifiedAs == testEntry.getClassification()) {
                correctlyClassified++;
            }

            if(i % progressModulo == 0) {
                System.out.println("Progress: " + progress + "%");
                progress += 5;
            }

        }

        double percentage = ((double)correctlyClassified / (double)this.allEntries.length) * 100;
        System.out.println("Done! Correctly classified: " + correctlyClassified + "/" + this.allEntries.length + " (" + percentage + "%)");

        return correctlyClassified;
    }

    private void writeTrainEntries(final CSVEntry[] trainEntries, int leftOutEntryIndex) {

        for(int i = 0, j = 0; i < this.allEntries.length; i++) {
            if(i == leftOutEntryIndex) {
                continue;
            }
            trainEntries[j++] = this.allEntries[i];
        }

    }

}
