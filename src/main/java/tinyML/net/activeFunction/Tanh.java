package tinyML.net.activeFunction;

import tinyML.dataType.Vector;

public class Tanh implements iActiveFunc {

    // active: Y = (e^(2*Z) - 1)) / (e^(2*Z) + 1))
    @Override
    public Vector active(Vector sum) {
        Vector result = Vector.create(sum.length);
        for (int i = 0; i < result.length; i++) {
            double temp = Math.exp(2 * sum.data[i]);
            result.data[i] = (temp - 1) / (temp + 1);
        }
        return result;
    }

    // derived: Y' = 1 - Y^2
    @Override
    public Vector derived(Vector sum) {
        Vector result = this.active(sum);
        for (int i = 0; i < result.length; i++) {
            result.data[i] = 1 - result.data[i] * result.data[i];
        }
        return result;
    }
}
