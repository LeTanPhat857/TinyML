package tinyML.net.layer.flatten;

import tinyML.net.layer.Layer;
import tinyML.net.layer.conv.ConvLayer;
import tinyML.net.layer.fulConn.FulConnLayer;
import tinyML.dataType.Tensor;
import tinyML.dataType.Vector;

public class Flatten extends Layer {

    // fields
    private double[] output;

    private double[][][] error;

    private FulConnLayer nextLayer;

    private ConvLayer preLayer;

    // constructor
    public Flatten(Tensor input, Vector output) {
        this.error = input.data;
        this.output = output.data;
    }

    // methods
    // set up
    public String getInfo() {
        return "flatten";
    }


    public void createLayer() {
        //do nothing
    }

    // forward
    public void computeOutput() {
        double[][][] inputData = new double[1][2][3]; // get output from pre layer
        int height = inputData.length;
        int width = inputData[0].length;
        int depth = inputData[0][0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < depth; k++) {
                    this.output[i * height * width + j * width + k] = inputData[i][j][k];
                }
            }
        }
    }

    public double[] getOutput() {
        return null;
    }

    // backward
    public void computeError() {

    }

    public double[] getError() {
        double[] result = new double[10];

        return result;
    }

    // connect
    public void setNextLayer(FulConnLayer nextLayer) {
        this.nextLayer = nextLayer;
    }


    public void setPreLayer(ConvLayer preLayer) {
        this.preLayer = preLayer;
    }


    public FulConnLayer getNextLayer() {
        return this.nextLayer;
    }


    public ConvLayer getPreLayer() {
        return this.preLayer;
    }

    // get
    public int getNumOfNeural() {
        return 0;
    }

}
