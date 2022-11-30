public class CSVEntry {

    private final float[] datapoints;
    private final int classification;

    public CSVEntry(float[] datapoints, int classification) {
        this.datapoints = datapoints;
        this.classification = classification;
    }

    public float[] getDatapoints() {
        return this.datapoints;
    }

    public int getClassification() {
        return this.classification;
    }

}
