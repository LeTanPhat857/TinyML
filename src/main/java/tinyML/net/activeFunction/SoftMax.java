package tinyML.net.activeFunction;

import tinyML.dataType.Vector;

public class SoftMax implements iActiveFunc {

    // active: Y = e^z / sum(e^z)
    @Override
    public Vector active(Vector sum) {
        Vector result = Vector.create(sum.length);
        double sumZ = 0;
        for (int i = 0; i < result.length; i++) {
            sumZ += Math.exp(sum.data[i]);
        }
        for (int i = 0; i < result.length; i++) {
            result.data[i] = Math.exp(sum.data[i]) / sumZ;
        }
        return result;
    }

    // derived:
    @Override
    public Vector derived(Vector sum) {
        return this.active(sum);
    }
}
