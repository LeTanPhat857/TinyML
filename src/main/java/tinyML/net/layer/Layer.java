package tinyML.net.layer;

import tinyML.net.activeFunction.iActiveFunc;
import tinyML.net.weightInit.iWeightInit;

public abstract class Layer implements iLayer {
    // fields
    protected iActiveFunc activeFunc;
    protected iWeightInit weightInit;

    @Override
    public abstract String getInfo();

    // methods
    @Override
    public void setActiveFunction(iActiveFunc activationFunction) {
            this.activeFunc = activationFunction;
    }

    @Override
    public void setWeightInit(iWeightInit weightInit) {
            this.weightInit = weightInit;
    }
}
