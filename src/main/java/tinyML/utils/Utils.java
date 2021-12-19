package tinyML.utils;

public class Utils {
    public static String[] convertDoubleArrayToStringArray(double[] doubleArray) {
        String[] stringArray = new String[doubleArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = String.valueOf((int) (doubleArray[i] * 100));

        }
        return stringArray;
    }

    public static int findIndexOfMax(double[] array) {
        int maxIndex = 0;
        double max = array[maxIndex];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static double[][][] convertVectorToTensor(double[] vec, int height, int width, int depth) {
        double[][][] result = new double[height][width][depth];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < depth; k++) {
                    result[i][j][k] = vec[i * height + j * width + k];
                }
            }
        }
        return result;
    }

    public static double[] convertTensorToVector(double[][][] tensor, int height, int width, int depth) {
        double[] result = new double[height * width * depth];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < depth; k++) {
                    result[i * height + j * width + k] = tensor[i][j][k];
                }
            }
        }
        return result;
    }
}
