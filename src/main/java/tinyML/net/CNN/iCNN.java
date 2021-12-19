package tinyML.net.CNN;

import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.iNet;
import tinyML.dataType.Matrix;
import tinyML.dataType.Tensor;

public interface iCNN extends iNet{

    public iCNN addConvLayer(int nKernel, Matrix kernel, iActiveFunc activeFunc, Tensor input);

    public iCNN addConvLayer(int nKernel, Matrix kernel, iActiveFunc activeFunc);

    public iCNN addPoolLayer(String style, Matrix kernel);

    public iCNN addFlattenLayer();

    public iCNN addFullyConnLayer(int nNeural, iActiveFunc activeFunc);
}
