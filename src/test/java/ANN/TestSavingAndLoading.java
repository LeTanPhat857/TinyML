package ANN;

import tinyML.dataReader.IdxReader;
import tinyML.net.ANN.ANN;
import tinyML.net.activeFunction.ActiveFunc;

import java.io.IOException;

public class TestSavingAndLoading {

	// test1: test saving
	public static void test1() throws IOException {
		// create net
		ANN ANN = new ANN();
		ANN.addFulConnLayer(15, ActiveFunc.get("Sigmoid"));
		ANN.build();

		// saving
		ANN.saveCurrentNet("trained/randomNet");
	}

	// test2: load trained net
	public static void test2() throws Exception {
		// create net
		ANN ANN = new ANN();

		// load trained net
		ANN.loadTrainedNet("trained/32-32-Net");

		// data test
		IdxReader idxReader1 = new IdxReader();
//		double[][] dataTestSet = dataReader1.readDataSet("data/MNIST/t10k-images.idx3-ubyte");
//		double[][] labelTestSet = dataReader1.readLabelSet("data/MNIST/t10k-labels.idx1-ubyte");

		// test
//		System.out.println("predict correction: " + net.test(dataTestSet, labelTestSet));
	}

	// run test
	public static void main(String[] args) throws Exception {
		test2();
	}
}
