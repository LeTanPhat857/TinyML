package tinyML.net.weightInit;

public class RandomWeight implements iWeightInit {

    public double[][][] randomTen(int height, int width, int depth) {
        double[][][] result = new double[height][width][depth];
        for (int i = 0; i < height; i++) {
            result[i] = randomMat(width, depth);
        }
        return result;    }

    public double[][] randomMat(int height, int width) {
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            result[i] = randomVec(width);
        }
        return result;
    }

    public double[] randomVec(int length) {
        double[] result = new double[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = -0.5 + Math.random();
        }
        return result;
    }
}
