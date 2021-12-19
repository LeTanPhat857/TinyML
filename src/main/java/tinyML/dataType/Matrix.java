package tinyML.dataType;

public class Matrix {
    // fields
    public int height;
    public int width;
    public double[][] data;

    // constructor
    private Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.data = new double[height][width];
    }

    private Matrix(double[][] data) {
        this.height = data.length;
        this.width = data[0].length;
        this.data = data;
    }

    // methods
    public static Matrix create(int height, int width) {
        return new Matrix(height, width);
    }

    public static Matrix create(double[][] data) {
        return new Matrix(data);
    }

    public Matrix tranpose() {
        Matrix result = Matrix.create(this.width, this.height);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[j][i];
            }
        }
        return result;
    }

    public Vector multi(Vector vector) {
        Vector result = Vector.create(this.height);
        try {
            if (this.width != vector.length) {
                throw new Exception("Matrix " + this.height + "x" + this.width + ", vector " + vector.length);
            }
            for (int i = 0; i < result.length; i++) {
                result.data[i] = Vector.create(this.data[i]).multi(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Matrix multi(double num) {
        Matrix result = Matrix.create(this.height, this.width);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[i][j] * num;
            }
        }
        return result;
    }

    public double sum() {
        double sum = 0;
        for (double[] row : this.data) {
            for (double element : row) {
                sum += element;
            }
        }
        return sum;
    }

    public Matrix eleWiseMulti(Matrix matrix) {
        if (this.height != matrix.height || this.width != this.width) {
            new Exception("mat1: " + this.height + "x" + this.width + ", mat2: " + matrix.height + "x" + matrix.width).printStackTrace();
        }
        Matrix result = Matrix.create(this.height, this.width);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[i][j] * matrix.data[i][j];
            }
        }
        return result;
    }

    public String print() {
        String result = "";
        for (double[] vec : this.data) {
            result += Vector.create(vec).print() + "\n";
        }
        return result;
    }

    public Matrix plus(Matrix other) {
        Matrix result = Matrix.create(this.height, this.width);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return result;
    }

    public Matrix subtract(Matrix other) {
        Matrix result = Matrix.create(this.height, this.width);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return result;
    }

    public Matrix divide(double num) {
        Matrix result = Matrix.create(this.height, this.width);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[i][j] / num;
            }
        }
        return result;
    }
}
