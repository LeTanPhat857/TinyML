package tinyML.net.weightInit;

public interface iWeightInit {

    public double[][][] randomTen(int height, int width, int depth);

    public double[][] randomMat(int height, int width);

    public double[] randomVec(int length);
}
