package main;

import main.classifier.IClassifier;

public class ProgramArgs {

    private final IClassifier classifier;
    private final int n;
    private final int d;
    private final String filename;

    public ProgramArgs(IClassifier classifier, int n, int d, String filename) {
        this.classifier = classifier;
        this.n = n;
        this.d = d;
        this.filename = filename;
    }

    public IClassifier getClassifier() {
        return this.classifier;
    }

    public int getN() {
        return this.n;
    }

    public int getD() {
        return this.d;
    }

    public String getFilename() {
        return this.filename;
    }

}
