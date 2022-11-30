package main.classifier;

public class KMajorityManhattanClassifier extends KMajorityClassifierBase {

    public KMajorityManhattanClassifier(int kMajority) {
        super(kMajority);
    }

    @Override
    public double distance(double[] trainDatapoints, double[] testDatapoints) {
        double distance = 0.0D;
        for(int i = 0; i < trainDatapoints.length; i++) {
            distance += Math.abs(trainDatapoints[i] - testDatapoints[i]);
        }
        return distance;
    }

}
