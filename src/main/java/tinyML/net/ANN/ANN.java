package tinyML.net.ANN;

import tinyML.net.Net;
import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.layer.fulConn.FulConnLayer;
import tinyML.net.optimizer.iOptimizer;
import tinyML.net.weightInit.iWeightInit;
import tinyML.dataType.Matrix;
import tinyML.dataType.Vector;

import java.util.ArrayList;
import java.util.List;

public class ANN extends Net {

    // fields
    private List<FulConnLayer> fulConnLayers;

    // constructor
    public ANN() {
        super();
        fulConnLayers = new ArrayList<>();
    }

    // methods
    public String getInfo() {
        // number of input
        String result = "ANN-" + getLayer(0).getNumIn();
        // number of neural
        for (FulConnLayer layer : fulConnLayers) {
            result += "-" + layer.getNumOut();
        }
        return result;
    }

    public ANN setWeightInit(iWeightInit weightInit) {
        for (FulConnLayer fulConnLayer : fulConnLayers) {
            fulConnLayer.setWeightInit(weightInit);
        }
        return this;
    }

    public ANN setOptimizer(iOptimizer optimizer) {
        this.optimizer = optimizer;
        optimizer.setNet(this);
        return this;
    }

    public ANN addFulConnLayer(int nNeural, iActiveFunc activeFunc, int nInput) {
        fulConnLayers.add(new FulConnLayer(nNeural, activeFunc, nInput));
        return this;
    }

    public ANN addFulConnLayer(int nNeural, iActiveFunc activeFunc) {
        fulConnLayers.add(new FulConnLayer(nNeural, activeFunc));
        return this;
    }

    public FulConnLayer getLayer(int index) {
        return this.fulConnLayers.get(index);
    }


    public int getNumOfLayers() {
        return this.fulConnLayers.size();
    }

    public ANN build() {
        int size = fulConnLayers.size();
        // set pre and next layer, createLayer
        for (int i = 0; i < size - 1; i++) {
            getLayer(i).setNextLayer(fulConnLayers.get(i + 1));
            fulConnLayers.get(i + 1).setPreLayer(getLayer(i));
            getLayer(i).createLayer();
        }
        // create last layer
        fulConnLayers.get(size - 1).createLayer();
        return this;
    }

    public Vector predict(Vector data) {
        try {
            int size = fulConnLayers.size();
            // set data for input layer
            fulConnLayers.get(0).computeOutput(data);
            // compute output for all hidden layer
            for (int i = 1; i < size; i++) {
                getLayer(i).computeOutput();
            }
            // return final result
            return fulConnLayers.get(size - 1).getOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public long train(Matrix dataSet, Matrix labelSet) {
        try {
            long startTime = System.currentTimeMillis();
            optimizer.train(dataSet, labelSet);
            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double test(Matrix dataTestSet, Matrix labelTestSet) {
        int counter = 0;
        for (int i = 0; i < dataTestSet.height; i++) {
            // find max
            Vector predict = this.predict(Vector.create(dataTestSet.data[i]));
            int maxIndex = 0;
            double maxValue = predict.data[0];
            int label = 0;
            for (int j = 1; j < getLayer(getNumOfLayers() - 1).getNumOut(); j++) {
                if (maxValue < predict.data[j]) {
                    maxIndex = j;
                    maxValue = predict.data[j];
                }
                if (labelTestSet.data[i][j] == 1) {
                    label = j;
                }
            }
            // compare
            if (maxIndex == label) {
                counter++;
            }
        }
        return (counter * 1.0) / dataTestSet.height;
    }


    public boolean saveCurrentNet(String currentNetPath) {

        return false;
    }

    public boolean loadTrainedNet(String trainedNetPath) {

        return false;
    }
}
