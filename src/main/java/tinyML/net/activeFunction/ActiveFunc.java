package tinyML.net.activeFunction;

public class ActiveFunc {
    public static iActiveFunc get(String type) {
        if (type.equalsIgnoreCase("Sigmoid")) {
            return new Sigmoid();
        }
        if (type.equalsIgnoreCase("ReLU")) {
            return new ReLU();
        }
        if (type.equalsIgnoreCase("Tanh")) {
            return new Tanh();
        }
        if (type.equalsIgnoreCase("SoftMax")) {
            return new SoftMax();
        }
        return null;
    }
}
