package main.classifier;

public class KMajorityEuclideanClassifier extends KMajorityClassifierBase {

    public KMajorityEuclideanClassifier(int kMajority) {
        super(kMajority);
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
