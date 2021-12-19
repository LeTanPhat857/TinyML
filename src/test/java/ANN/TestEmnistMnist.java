package ANN;

import tinyML.dataReader.IdxReader;
import tinyML.dataReader.iDataReader;
import tinyML.net.ANN.ANN;
import tinyML.net.activeFunction.ActiveFunc;
import tinyML.net.optimizer.Optimizer;
import tinyML.net.weightInit.WeightInit;
import tinyML.utils.ImgUtils;
import tinyML.dataType.Vector;

public class TestEmnistMnist {
    // test1: test training
    public static void test() throws Exception {
        // create net
        ANN net = new ANN()
                .addFulConnLayer(32, ActiveFunc.get("sigmoid"), 784)
                .addFulConnLayer(10, ActiveFunc.get("sigmoid"))
                .setWeightInit(WeightInit.get("randomWeight"))
//                .setOptimizer(Optimizer.GD("ann", 30))
                .setOptimizer(Optimizer.SGD("ann", 0.3))
//                .setOptimizer(Optimizer.miniBatch_SGD("ann", 10, 3))
                .build();
        System.out.print("create net...");
        System.out.println(net.getInfo());
        // dataSet and labelSet
        System.out.print("load data...");
        iDataReader idxReader = new IdxReader().setNormalization(true).setNumOfClass(10);
        idxReader.loadTrainingData("src/main/java/mnist/train-images.idx3-ubyte");
        idxReader.loadTrainingLabel("src/main/java/mnist/train-labels.idx1-ubyte");
        idxReader.loadTestingData("src/main/java/mnist/t10k-images.idx3-ubyte");
        idxReader.loadTestingLabel("src/main/java/mnist/t10k-labels.idx1-ubyte");
        System.out.println(idxReader.getNumOfTrainImgs() + " training examples, " + idxReader.getNumOfTestImgs() + " testing examples");
        // show image
        Thread imgPrinter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double[][] data = idxReader.getTrainingData().data;
                    for (int i = 0; i < data.length; i++){
                        if (idxReader.getNormalization()) {
                            ImgUtils.getInstance("training data image").showImage(Vector.create(data[i]).multi(255).data, idxReader.getNumOfRows(), idxReader.getNumOfCols(), (int) net.predict(Vector.create(data[i])).findMaxIndex() + "");
                        } else {
                            ImgUtils.getInstance("training data image").showImage(data[i], idxReader.getNumOfRows(), idxReader.getNumOfCols(), (int) net.predict(Vector.create(data[i])).findMaxIndex() + "");
                        }
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        imgPrinter.setDaemon(true);
        imgPrinter.start();
        // training
        System.out.println("start training...");
        for (int i = 0; i < 30; i++) {
            System.out.print("epoch " + (i + 1));
            System.out.print("...time: " + net.train(idxReader.getTrainingData(), idxReader.getTrainingLabel()) + "mls");
            System.out.println(", accuracy: " + net.test(idxReader.getTestingData(), idxReader.getTestingLabel()));
        }
        System.out.println("end.");
        // stop
        imgPrinter.stop();
    }

    // run test
    public static void main(String[] args) throws Exception {
        test();
    }
}
