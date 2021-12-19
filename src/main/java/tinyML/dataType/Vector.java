package tinyML.dataType;

import java.io.IOException;

public class Vector {
    // fields
    public int length;

    public double[] data;

    // constructor
    private Vector(int length) {
        this.length = length;
        this.data = new double[length];
    }

    private Vector(double[] data) {
        this.length = data.length;
        this.data = data;
    }

    // methods
    public static Vector create(int length) {
        return new Vector(length);
    }

    public static Vector create(double[] data) {
        return new Vector(data);
    }

    public boolean equal(Vector other) {
        int size1 = this.length;
        int size2 = other.length;
        try {
            if (size1 != size2) {
                throw new IOException("Vector 1 has size: " + size1 + "; Vector 2 has size: " + size2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Vector plus(Vector other) {
        if (this.equal(other)) {
            Vector result = Vector.create(this.length);
            for (int i = 0; i < result.length; i++) {
                result.data[i] = this.data[i] + other.data[i];
            }
            return result;
        }
        return null;
    }

    public Vector eleWiseMulti(Vector other) {
        if (this.equal(other)) {
            Vector result = Vector.create(this.length);
            for (int i = 0; i < result.length; i++) {
                result.data[i] = this.data[i] * other.data[i];
            }
            return result;
        }
        return null;
    }

    public double multi(Vector other) {
        if (this.equal(other)) {
            double result = 0;
            for (int i = 0; i < this.length; i++) {
                result += this.data[i] * other.data[i];
            }
            return result;
        }
        return 0;
    }

    public Vector multi(double num) {
        Vector result = Vector.create(this.length);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = num * this.data[i];
        }
        return result;
    }

    public Vector subtract(Vector other) {
        if (this.equal(other)) {
            Vector result = Vector.create(this.length);
            for (int i = 0; i < result.length; i++) {
                result.data[i] = this.data[i] - other.data[i];
            }
            return result;
        }
        return null;
    }

    public Vector subtract(double num) {
        Vector result = Vector.create(this.length);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = this.data[i] - num;
        }
        return result;
    }

    public Vector numSubtract(double num) {
        Vector result = Vector.create(this.length);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = num - this.data[i];
        }
        return result;
    }

    public Vector divide(Vector other) {
        Vector result = Vector.create(this.length);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = this.data[i] / other.data[i];
        }
        return result;
    }

    public Vector divide(double num) {
        Vector result = Vector.create(this.length);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = this.data[i] / num;
        }
        return result;
    }

    public Matrix multiMat(Vector vector) {
        Matrix result = Matrix.create(this.length, vector.length);
        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                result.data[i][j] = this.data[i] * vector.data[j];
            }
        }
        return result;
    }

    public String print() {
        String result = "";
        for (double element : this.data) {
            result += element + "  ";
        }
        return result;
    }

    public double findMax() {
        double max = this.data[0];
        for (int i = 1; i < this.length; i++) {
            if (max < this.data[i]) {
                max = this.data[i];
            }
        }
        return max;
    }

    public double findMaxIndex() {
        double max = this.data[0];
        int index = 0;
        for (int i = 1; i < this.length; i++) {
            if (max < this.data[i]) {
                max = this.data[i];
                index = i;
            }
        }
        return index;
    }
}
