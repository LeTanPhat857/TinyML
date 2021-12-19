package tinyML.net.ANN;

import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.iNet;
import tinyML.net.layer.fulConn.FulConnLayer;

public interface iANN extends iNet{

    public iANN addFulConnLayer(int nNeural, iActiveFunc activeFunc, int nInput);

    public iANN addFulConnLayer(int nNeural, iActiveFunc activeFunc);

    public FulConnLayer getLayer(int index);

    public int getNumOfLayers();
}
