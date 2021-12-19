package tinyML.net.layer.fulConn;

import tinyML.net.activeFunction.Sigmoid;
import tinyML.net.activeFunction.SoftMax;
import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.weightInit.iWeightInit;
import tinyML.dataType.Matrix;
import tinyML.dataType.Vector;

public class FulConnLayer {

    // fields
    private final int nOutput;

    private Matrix weights;
    private Vector biases;

    private Vector sum;
    private Vector active;

    private Vector inputError;
    private Matrix weightError;
    private Vector biasError;

    private FulConnLayer nextLayer;
    private FulConnLayer preLayer;

    protected iActiveFunc activeFunc;
    protected iWeightInit weightInit;

    private Vector input; // for input layer

    // constructor
    public FulConnLayer(int nOutput) {
        this.nOutput = nOutput;
    }

    public FulConnLayer(int nOutput, iActiveFunc activeFunc) {
        this.nOutput = nOutput;
        this.activeFunc = activeFunc;
    }

    public FulConnLayer(int nOutput, iActiveFunc activeFunc, int inputSize) {
        this.nOutput = nOutput;
        this.activeFunc = activeFunc;
        this.input = Vector.create(inputSize);
    }

    // methods
    // set up
    public void setActiveFunction(iActiveFunc activationFunction) {
        this.activeFunc = activationFunction;
    }

    public void setWeightInit(iWeightInit weightInit) {
        this.weightInit = weightInit;
    }

    public void createLayer() {
        // get nInput
        int nInput;
        if (this.input == null) {
            nInput = this.preLayer.getNumOut();
        } else {
            nInput = input.length;
        }
        // set up weight matrix
        this.weights = Matrix.create(weightInit.randomMat(this.nOutput, nInput));
        // set up bias vector
        this.biases = Vector.create(weightInit.randomVec(this.nOutput));
        // set up delta matrix
        this.weightError = Matrix.create(weightInit.randomMat(this.nOutput, nInput));
        // set up delta vector
        this.biasError = Vector.create(weightInit.randomVec(this.nOutput));
        // set up error
        this.inputError = Vector.create(this.nOutput);
    }

    // forward
    public void computeOutput(Vector input) {
        // set input
        this.input = input;
        // 1. compute sum: Z = W * X + Bias
        this.sum = this.weights.multi(this.input).plus(this.biases);
        // 2. active: A = active(Z)
        this.active = this.activeFunc.active(this.sum);
    }

    public void computeOutput() {
        // 1. compute sum: Z = W * X + Bias
        this.sum = this.weights.multi(preLayer.getOutput()).plus(this.biases);
        // 2. active: A = active(Z)
        this.active = this.activeFunc.active(this.sum);
    }

    public Vector getOutput() {
        return this.active;
    }

    // backward
    // compute error for output layer
    public void computeError(Vector predictVec, Vector actualVec) {
        // output layer is softmax
        if (activeFunc instanceof SoftMax) {
            this.inputError = predictVec.subtract(actualVec);
        }
        // output layer is sigmoid
        if (activeFunc instanceof Sigmoid) {
            this.inputError = this.crossEntropy(predictVec, actualVec).eleWiseMulti(activeFunc.derived(this.sum));
        }
        // update
        updateError();
    }

    public void computeError() {
        // compute error (not output layer): (nextLayerWeightMatTranpose * nextLayerError) .* active'(sumVec)
        this.inputError = nextLayer.getWeights().tranpose().multi(nextLayer.getInputError()).eleWiseMulti(activeFunc.derived(this.sum));
        // update
        updateError();
    }

    public Vector getInputError() {
        return this.inputError;
    }

    // gradient descent
    public void update(double alpha, int len) {
        // update weight: weight=bias-alpha*weightError/len
        this.weights = this.weights.subtract(this.weightError.multi(alpha).divide(len));
        // update bias: bias=bias-alpha*biasError/len
        this.biases = this.biases.subtract(this.biasError.multi(alpha).divide(len));
        // reset deltaMatrix
        this.weightError = Matrix.create(weightError.height, weightError.width);
        // reset deltaVector
        this.biasError = Vector.create(biasError.length);
    }

    // connect
    public FulConnLayer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(FulConnLayer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public FulConnLayer getPreLayer() {
        return preLayer;
    }

    public void setPreLayer(FulConnLayer preLayer) {
        this.preLayer = preLayer;
    }

    // get
    public int getNumOut() {
        return this.nOutput;
    }

    public int getNumIn() {
        return this.input.length;
    }

    public Matrix getWeights() {
        return weights;
    }

    // helper
    private Vector mse(Vector predictVec, Vector actualVec) {
        // mean square error: (y-y*)/len
        Vector derive = this.activeFunc.derived(this.sum);
        return predictVec.subtract(actualVec).eleWiseMulti(derive).divide(predictVec.length);
    }

    private Vector crossEntropy(Vector predictVec, Vector actualVec) {
        // cross entropy: (((1 - y*)/(1-y) - y*/y).*f')/len
        Vector derive = this.activeFunc.derived(this.sum);
        return actualVec.numSubtract(1).divide(predictVec.numSubtract(1)).subtract(actualVec.divide(predictVec)).eleWiseMulti(derive).divide(predictVec.length);
    }

    private void updateError() {
        // update delta vector
        this.biasError = this.biasError.plus(this.inputError);
        // update delta matrix
        Matrix tempWeightError;
        if (this.input == null) {
            tempWeightError = this.inputError.multiMat(this.preLayer.getOutput());
        } else {
            tempWeightError = this.inputError.multiMat(this.input);
        }
        this.weightError = this.weightError.plus(tempWeightError);
    }
}
