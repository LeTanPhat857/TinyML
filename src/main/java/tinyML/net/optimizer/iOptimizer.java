package tinyML.net.optimizer;

import tinyML.net.iNet;
import tinyML.dataType.Matrix;

public interface iOptimizer {

    public void setNet(iNet net);

    public void train(Matrix dataSet, Matrix labelSet);
}
