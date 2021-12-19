package tinyML.net.activeFunction;

import tinyML.dataType.Vector;

public class ReLU implements iActiveFunc {

    // active: Y = max(0, Z)
    @Override
    public Vector active(Vector sum) {
        Vector result = Vector.create(sum.length);
        for (int i = 0; i < sum.length; i++) {
            result.data[i] = Math.max(0, sum.data[i]);
        }
        return result;
    }

    // derived: Y' = 1 if Z > 0; Y' = 0 if Z <= 0
    @Override
    public Vector derived(Vector sum) {
        Vector result = Vector.create(sum.length);
        for (int i = 0; i < sum.length; i++) {
            if (sum.data[i] > 0) {
                result.data[i] = 1;
            } else {
                result.data[i] = 0;
            }
        }
        return result;
    }
}
