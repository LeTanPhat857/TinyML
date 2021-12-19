package tinyML.net.activeFunction;

import tinyML.dataType.Vector;

public class Sigmoid implements iActiveFunc {

    // active: Y = 1 / (1 + e^(-Z))
    @Override
    public Vector active(Vector sum) {
        Vector result = Vector.create(sum.length);
        for (int i = 0; i < sum.length; i++) {
            result.data[i] = 1 / (1 + Math.exp(-sum.data[i]));
        }
        return result;
    }

    // derived: Y' = Y * ( 1 - Y)
    @Override
    public Vector derived(Vector sum) {
        Vector result = active(sum);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = result.data[i] * (1 - result.data[i]);
        }
        return result;
    }
}
