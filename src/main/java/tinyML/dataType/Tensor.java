package tinyML.dataType;

public class Tensor {
    // fields
    public int height;
    public int width;
    public int depth;
    public double[][][] data;

    // constructor
    private Tensor(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.data = new double[height][width][depth];
    }

    private Tensor(double[][][] data) {
        this.height = data.length;
        this.width = data[0].length;
        this.depth = data[0][0].length;
        this.data = data;
    }

    // methods
    public static Tensor get(int height, int width, int depth) {
        return new Tensor(height, width, depth);
    }

    public static Tensor get(double[][][] data) {
        return new Tensor(data);
    }

    public double sum() {
        double sum = 0;
        for (double[][] height : this.data) {
            for (double[] width : height) {
                for (double depth : width) {
                    sum += depth;
                }
            }
        }
        return sum;
    }

    public Tensor eleWiseMulti(Tensor tensor) {
        if (this.height != tensor.height || this.width != tensor.width || this.depth != tensor.depth) {
            new Exception("mat1: " + this.height + "x" + this.width + "x" + this.depth + ", mat2: " + tensor.height + "x" + tensor.width + "x" + tensor.depth).printStackTrace();
        }
        Tensor result = Tensor.get(this.depth, this.height, this.width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < depth; k++) {
                    result.data[i][j][k] = this.data[i][j][k] * tensor.data[i][j][k];
                }
            }
        }
        return result;
    }

    public String print(double[][][] ten) {
        String result = "";
        for (int k = 0; k < ten[0][0].length; k++) {
            result += Matrix.create(this.data[k]);
            result += "\n";
        }
        return result;
    }
}
