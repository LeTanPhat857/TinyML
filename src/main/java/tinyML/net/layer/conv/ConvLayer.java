package tinyML.net.layer.conv;

import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.layer.Layer;
import tinyML.dataType.Matrix;
import tinyML.dataType.Tensor;
import tinyML.utils.Utils;

public class ConvLayer extends Layer {
    // fields
    // padding
    private int padding = 0; // fixed

    // stride
    private int stride = 1; // fixed

    // kernels - list of weight matrix, kernels: nKernel x kernelHeight x kernelWidth x kernelDepth
    private double[][][][] kernels;

    // biases vector - contain bias of kernels
    private double[] biases;

    // sum tensor - contain sum data, Z: nKernel x kernelRow x kernelCol
    private double[][][] sum;

    // active tensor- contain active data
    private double[][][] active;

    // input tensor - instead of input layer
    private double[][][] input;

    //
    private ConvLayer nextLayer;

    private ConvLayer preLayer;

    // constructor
    public ConvLayer(int nKernel, Matrix kernelShape, iActiveFunc activeFunc, Tensor input) {
        this.kernels = new double[nKernel][kernelShape.height][kernelShape.width][input.depth];
        this.biases = new double[nKernel];
        this.input = new double[input.height][input.width][input.depth];
        this.activeFunc = activeFunc;
    }

    public ConvLayer(int nKernel, Matrix kernelShape, iActiveFunc activeFunc) {
        this.kernels = new double[nKernel][kernelShape.height][kernelShape.width][];
        this.biases = new double[nKernel];
        this.activeFunc = activeFunc;
    }

    // methods

    public void createLayer() {
        //
        if (input == null) {
            this.active = new double[preLayer.getActiveHeight()][preLayer.getActiveWidth()][getNumOfKernel()];
        } else {
            this.active = new double[getInputHeight()][getInputWidth()][getNumOfKernel()];
        }
        // ?? depth of kernels and biases
        int depth = 0;
        if (input == null) {
            this.kernels = new double[getNumOfKernel()][getKernelHeight()][getKernelHeight()][getPreLayer().getNumOfKernel()];
        }
        // create kernelTen
        for (int i = 0; i < getNumOfKernel(); i++) {
            this.kernels[i] = this.weightInit.randomTen(getKernelHeight(), getKernelWidth(), getKernelDepth());
        }
        // create biases
        this.biases = this.weightInit.randomVec(getKernelDepth());
    }


    public String getInfo() {
        return "conv " + getNumOfKernel() + "x" + getKernelWidth() + "x" + getKernelHeight();
    }

    public void setInputData(double[] inputData) {
        this.input = Utils.convertVectorToTensor(inputData, getInputHeight(), getInputWidth(), getInputDepth());
    }

    public void computeOutput() {
        // 1. compute sum: Z = X o Kernel + Bias
        if (this.input != null) {
//            this.sum = MatUtils.conv(this.kernels, this.input, this.biases);
        } else {
//            this.
        }
        // 2. compute active: A = active(Z)
//        this.active = this.activeFunc.active(this.sum);
    }


    public double[] getOutput() {
        return Utils.convertTensorToVector(this.active, preLayer.getActiveHeight(), preLayer.getActiveWidth(), preLayer.getActiveDepth());
    }


    public void setNextLayer(ConvLayer nextLayer) {
        this.nextLayer = nextLayer;
    }


    public void setPreLayer(ConvLayer preLayer) {
        this.preLayer = preLayer;
    }


    public ConvLayer getNextLayer() {
        return this.nextLayer;
    }


    public ConvLayer getPreLayer() {
        return this.preLayer;
    }


    public int getNumOfNeural() {
        return getNumOfKernel();
    }


    public int getNumOfKernel() {
        return this.kernels.length;
    }


    public int getActiveHeight() {
        return this.active.length;
    }


    public int getActiveWidth() {
        return this.active[0].length;
    }


    public int getActiveDepth() {
        return this.active[0][0].length;
    }


    public double[][][] getActive2() {
        return this.active;
    }


    public int getKernelHeight() {
        return this.kernels[0].length;
    }

    public int getKernelWidth() {
        return this.kernels[0][0].length;
    }

    public int getKernelDepth() {
        return this.kernels[0][0][0].length;
    }

    public int getInputHeight() {
        return this.input.length;
    }

    public int getInputWidth() {
        return this.input[0].length;
    }

    public int getInputDepth() {
        return this.input[0][0].length;
    }
}
