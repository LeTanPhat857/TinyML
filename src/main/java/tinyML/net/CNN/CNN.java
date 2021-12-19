package tinyML.net.CNN;

import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.layer.conv.ConvLayer;
import tinyML.net.layer.flatten.Flatten;
import tinyML.net.layer.iLayer;
import tinyML.net.layer.pool.PoolLayer;
import tinyML.net.weightInit.iWeightInit;
import tinyML.dataType.Matrix;
import tinyML.dataType.Tensor;
import tinyML.dataType.Vector;

import java.util.ArrayList;
import java.util.List;

public class CNN{
    // fields
    private List<iLayer> layers;

    // constructor
    public CNN() {
        this.layers = new ArrayList<>();
    }

    // methods
    
    public CNN addConvLayer(int nKernel, Matrix kernelShape, iActiveFunc activeFunc, Tensor input) {
        this.layers.add(new ConvLayer(nKernel, kernelShape, activeFunc, input));
        return this;
    }

    
    public CNN addConvLayer(int nKernel, Matrix kernelShape, iActiveFunc activeFunc) {
        this.layers.add(new ConvLayer(nKernel, kernelShape, activeFunc));
        return this;
    }

    
    public CNN addPoolLayer(String type, Matrix kernel) {
        this.layers.add(new PoolLayer(type, kernel));
        return this;
    }

    
    public CNN addFlattenLayer(Tensor input, Vector output) {
        this.layers.add(new Flatten(input, output));
        return this;
    }

    
    public CNN addFullyConnLayer(int nNeural, iActiveFunc activeFunc) {
//        this.layers.add(new FulConnLayer(nNeural, activeFunc));
        return this;
    }

    
    public String getInfo() {
        String result = "CNN";
        for (iLayer layer : this.layers) {
            result += " - " + layer.getInfo();
        }
        return result;
    }

    
    public CNN setWeightInit(iWeightInit weightInit) {
        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).setWeightInit(weightInit);
        }
        return this;
    }

    
    public CNN build() {
        int layerSize = layers.size();
        for (int i = 0; i < layerSize - 1; i++) {
//            layers.get(i).setNextLayer(layers.get(i + 1));
//            layers.get(i + 1).setPreLayer(layers.get(i));
            layers.get(i).createLayer();
        }
        layers.get(layerSize - 1).createLayer();
        return this;
    }

    
    public double[] predict(double[] data) {
        int layerSize = layers.size();
//        layers.get(0).setInput(data);
        for (int i = 0; i < layerSize; i++) {
            layers.get(i).computeOutput();
        }
        return layers.get(layerSize - 1).getOutput();
    }

    
    public void train(double[][] dataSet, double[][] labelSet) {

    }

    
    public void saveCurrentNet(String trainedNet) {

    }

    
    public void loadTrainedNet(String trainedNet) {

    }

    
    public int test(double[][] dataTestSet, double[][] labelTestSet) {
        return 0;
    }
}
