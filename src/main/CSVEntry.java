package main;

public class CSVEntry {

    private final double[] datapoints;
    private final int classification;

    public CSVEntry(double[] datapoints, int classification) {
        this.datapoints = datapoints;
        this.classification = classification;
    }

    public double[] getDatapoints() {
        return this.datapoints;
    }

    public int getClassification() {
        return this.classification;
    }

}
