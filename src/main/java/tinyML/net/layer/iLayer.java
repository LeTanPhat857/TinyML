package tinyML.net.layer;

import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.weightInit.iWeightInit;

public interface iLayer {

    // active
    public void createLayer();

    public void computeOutput();

    // set
    public void setActiveFunction(iActiveFunc activationFunction);

    public void setWeightInit(iWeightInit weightInit);

    // get
    public int getNumOfNeural();

    public double[] getOutput();

    public String getInfo();
}
