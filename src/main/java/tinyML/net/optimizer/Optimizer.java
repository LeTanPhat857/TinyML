package tinyML.net.optimizer;

import tinyML.net.iNet;
import tinyML.dataType.Matrix;

public abstract class Optimizer implements iOptimizer {
    // fields
    protected iNet net;

    // methods
    @Override
    public void setNet(iNet net) {
        this.net = net;
    }

    @Override
    public abstract void train(Matrix dataSet, Matrix labelSet);

    // static
    public static iOptimizer miniBatch_SGD(String type, int miniBatch, double learningRate) {
        return new MiniBatchSGD(type, miniBatch, learningRate);
    }

    public static iOptimizer SGD(String type, double learningRate) {
        return new SGD(type, learningRate);
    }

    public static iOptimizer GD(String type, double learningRate) {
        return new GD(type, learningRate);
    }
}
