package tinyML.net;

import tinyML.net.optimizer.iOptimizer;

public abstract class Net implements iNet {

    // fields
    protected iOptimizer optimizer;

    // constructor
    protected Net() {
    }

    // methods
    public iNet setOptimizer(iOptimizer optimizer) {
        this.optimizer = optimizer;
        optimizer.setNet(this);
        return this;
    }
}

