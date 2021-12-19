package tinyML.net.layer.pool;

import tinyML.net.layer.Layer;
import tinyML.net.layer.conv.ConvLayer;
import tinyML.net.layer.iLayer;
import tinyML.dataType.Matrix;
import tinyML.utils.Utils;

public class PoolLayer extends Layer {

    // fields
    // padding
    private int padding = 0; // fixed

    // stride
    private int stride = 2; // fixed

    //
    private String type;

    //
    private Matrix kernel;

    //
    private double[][][] active;

    //
    private ConvLayer nextLayer;

    private ConvLayer preLayer;

    // constructor
    public PoolLayer(String type, Matrix kernel) {
        this.type = type;
        this.kernel = kernel;
    }

    // methods
    
    public String getInfo() {
        return "pool " + this.kernel.height + "x" + this.kernel.width;
    }

    
    public void createLayer() {
        this.active = new double[(preLayer.getActiveHeight() - kernel.height + padding) / stride + 1][(preLayer.getActiveWidth() + padding) / stride + 1][(preLayer.getActiveDepth() + padding) / stride + 1];
    }

    
    public void computeOutput() {
//        this.active = MatUtils.pool(this.kernel, Utils.convertVectorToTensor(preLayer.getOutput(), preLayer.getActiveHeight(), preLayer.getActiveWidth(), preLayer.getActiveDepth()), type);
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

    
    public iLayer getNextLayer() {
        return this.nextLayer;
    }

    
    public iLayer getPreLayer() {
        return this.preLayer;
    }

    
    public int getNumOfNeural() {
        return this.getNumOfKernel();
    }


    
    public int getNumOfKernel() {
        return 1;
    }

    
    public int getActiveHeight() {
        return this.kernel.height;
    }

    
    public int getActiveWidth() {
        return this.kernel.width;
    }

    
    public int getActiveDepth() {
        return 1;
    }

    
    public double[][][] getActive2() {
        return this.active;
    }
}
