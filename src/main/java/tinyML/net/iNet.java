package tinyML.net;

import tinyML.net.optimizer.iOptimizer;
import tinyML.net.weightInit.iWeightInit;
import tinyML.dataType.Matrix;
import tinyML.dataType.Vector;

public interface iNet {

    //
    public String getInfo();

    //
    public iNet setWeightInit(iWeightInit weightInit);

    public iNet setOptimizer(iOptimizer optimizer);

    public iNet build();

    //
    public Vector predict(Vector data);

    public long train(Matrix dataSet, Matrix labelSet);

    public double test(Matrix dataTestSet, Matrix labelTestSet);

    public boolean saveCurrentNet(String currentNetPath);

    public boolean loadTrainedNet(String trainedNetPath);
}
