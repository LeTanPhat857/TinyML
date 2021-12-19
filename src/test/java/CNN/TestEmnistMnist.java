//package test.CNN;
//
//import model.dataReader.IdxReader;
//import model.dataReader.iDataReader;
//import model.net.CNN.CNN;
//import model.net.activeFunction.ActiveFunc;
//import model.net.iNet;
//import model.net.optimizer.Optimizer;
//import model.net.weightInit.WeightInit;
//import utils.ImgUtils;
//import utils.MatUtils;
//import utils.Shape;
//
//import java.util.Arrays;
//
//public class TestEmnistMnist {
//    // test1: test training
//    public static void test1() throws Exception {
//        // create net
//        System.out.print("create net...");
//        iNet net = new CNN()
//                .addConvLayer(32, Shape.kernel(3, 3), ActiveFunc.get("relu"), Shape.input(28, 28, 1))
//                .addPoolLayer("max", Shape.kernel(2,2))
//                .addConvLayer(32, Shape.kernel(3, 3), ActiveFunc.get("relu"))
//                .addPoolLayer("max",Shape.kernel(2,2))
//                .addFullyConnLayer(128, ActiveFunc.get("sigmoid"))
//                .addFullyConnLayer(64, ActiveFunc.get("sigmoid"))
//                .addFullyConnLayer(10, ActiveFunc.get("sigmoid"))
//                .setWeightInit(WeightInit.get("randomWeight"))
//                .setOptimizer(Optimizer.miniBatch_SGD("cnn",  10, 1))
//                .build();
//        System.out.println(net.getInfo());
//
//        // dataSet and labelSet
//        System.out.print("load data...");
//        iDataReader idxReader = new IdxReader().setNormalization(true).setNumOfClass(10);
//        idxReader.loadTrainingData("src/main/java/data/mnist/train-images.idx3-ubyte");
//        idxReader.loadTrainingLabel("src/main/java/data/mnist/train-labels.idx1-ubyte");
//        idxReader.loadTestingData("src/main/java/data/mnist/t10k-images.idx3-ubyte");
//        idxReader.loadTestingLabel("src/main/java/data/mnist/t10k-labels.idx1-ubyte");
//        System.out.println(idxReader.getNumOfTrainImgs() + " training examples..." + idxReader.getNumOfTestImgs() + " testing examples");
//
//        System.out.println("predict: " + Arrays.toString(net.predict(idxReader.getTrainingData()[0])));
//
//        // show image
//        Thread imgPrinter = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (double[] data : idxReader.getTrainingData()) {
//                        if (idxReader.getNormalization()) {
//                            ImgUtils.getInstance("training data image").showImage(MatUtils.multipleNumToVec(255, data), idxReader.getNumOfRows(), idxReader.getNumOfCols());
//                        } else {
//                            ImgUtils.getInstance("training data image").showImage(data, idxReader.getNumOfRows(), idxReader.getNumOfCols());
//                        }
//                        Thread.sleep(2000);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        imgPrinter.setDaemon(true);
//        imgPrinter.start();
//
//        // training
//        System.out.print("train...");
//        long startTime = System.currentTimeMillis();
//        net.train(idxReader.getTrainingData(), idxReader.getTrainingLabel());
//        long endTime = System.currentTimeMillis();
//        System.out.print("training time: " + (endTime - startTime) / 1000 + "s");
//
//        // test
//        System.out.print("test...");
//        System.out.println("accuracy: " + net.test(idxReader.getTestingData(), idxReader.getTestingLabel()) * 1.0 / idxReader.getNumOfTestImgs());
//
//        imgPrinter.stop();
//    }
//
//    // run test
//    public static void main(String[] args) throws Exception {
//        test1();
//    }
//}
