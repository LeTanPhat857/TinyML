package tinyML.net.optimizer;

import tinyML.net.ANN.ANN;
import tinyML.dataType.Matrix;
import tinyML.dataType.Vector;

import java.util.Random;

public class GD extends Optimizer {

    // fields
    private double learningRate;

    // constructors
    public GD(String type, double learningRate) {
        this.learningRate = learningRate;
    }

    // methods
    @Override
    public void train(Matrix dataSet, Matrix labelSet) {
        try {
            // number of layer
            int nLayer = ((ANN) net).getNumOfLayers();

            // length of dataSet and labelSet
            int len = dataSet.height;

            // shuffle data
//            shuffleTrainingData(dataSet, labelSet);

            // for 1 miniBatch in (lenOfData / miniBatch)
            for (int i = 0; i < len; i++) {
                // step 1. forward propagation - predict
                Vector predictVec = ((ANN) net).predict(Vector.create(dataSet.data[i]));
                Vector desiredVec = Vector.create(labelSet.data[i]);
                // step 2. back propagation
                // step 2.1. compute error and update delta for outputLayer
//                ((ANN) net).getLayer(nLayer - 1).computeError(Vector.create(predictVec), Vector.create(desiredVec));
                ((ANN) net).getLayer(nLayer - 1).computeError(predictVec, desiredVec);
                // step 2.2. compute error and update delta for rest layer
                for (int k = nLayer - 2; k >= 0; k--) {
                    ((ANN) net).getLayer(k).computeError();
                }
            }
            // step 3. gradient descent - change weight and bias - from output to input
            for (int k = nLayer - 1; k >= 0; k--) {
                ((ANN) net).getLayer(k).update(learningRate, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shuffleTrainingData(Matrix dataSet, Matrix labelSet) {
        int len = dataSet.height;

        for (int i = 0; i < len; i++) {
            int randNum = new Random().nextInt(len);

            double[] tempData = dataSet.data[i];
            dataSet.data[i] = dataSet.data[randNum];
            dataSet.data[randNum] = tempData;

            double[] tempLabel = labelSet.data[i];
            labelSet.data[i] = labelSet.data[randNum];
            labelSet.data[randNum] = tempLabel;
        }
    }
}
