package utils.dataType;

class TensorTest {

//    @Test
//    void addPadding() {
//        double[][][] data = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
//        Tensor tensor = Tensor.create(data).addPadding(1);
//        System.out.println("height: " + tensor.height + ", width: " + tensor.width + ", depth: " + tensor.depth);
//        System.out.println(tensor.print());
//    }
//
//    @Test
//    void eleWisePlus() {
//        double[][][] data = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
//        Tensor tensor = Tensor.create(data);
//        System.out.println(tensor.print());
//        System.out.println(tensor.eleWisePlus(tensor).print());
//    }
//
//    @Test
//    void conv() {
//        double[][][] data = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
//        Tensor tensor = Tensor.create(data);
//        Tensor tensor1 = Tensor.create(data).addPadding(1);
//        Tensor tensor2 = tensor1.conv(tensor, 2);
//
//        System.out.println(tensor.print());
//        System.out.println("--------------------------------");
//        System.out.println(tensor1.print());
//        System.out.println("--------------------------------");
//        System.out.println(tensor2.print());
//        System.out.println("--------------------------------");
//        System.out.println(tensor2.sum());
//    }
//
//    @Test
//    void testConv() {
//        double[][][] data = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
//        Tensor tensor = Tensor.create(data);
//
//        System.out.println(tensor.print());
//        System.out.println("--------------------------------");
//        System.out.println(tensor.print());
//    }
//
//    @Test
//    void convertToVector() {
//        double[][][] data = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
//        Tensor tensor = Tensor.create(data);
//
//        System.out.println(tensor.print());
//        System.out.println("--------------------------------");
//        System.out.println(tensor.convertToVector().print());
//        System.out.println("--------------------------------");
//        System.out.println(tensor.convertToVector().convertToTensor(tensor.height, tensor.width, tensor.depth).print());
//    }
//
//    @Test
//    void maxPool() {
//        double[][][] rawData = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
//        Tensor tensor = Tensor.create(rawData);
//        Tensor data = tensor.maxPool(2);
//
//        System.out.println(tensor.print());
//        System.out.println("--------------------------------");
//        System.out.println(data.print());
//        System.out.println("--------------------------------");
//
//        int kernelHeight = 2;
//
//        Tensor result = Tensor.create(tensor.height, tensor.width, tensor.depth);
//        for (int k = 0; k < data.depth; k++) {
//            for (int i = 0, x = 0; i < data.height; i++, x += kernelHeight) {
//                for (int j = 0, y = 0; j < data.width; j++, y += kernelHeight) {
//                    // max
//                    double max = tensor.data[x][y][k];
//                    int h = x;
//                    int w = y;
//                    for (int m = 0; m < kernelHeight; m++) {
//                        for (int n = 0; n < kernelHeight; n++) {
//                            if (tensor.data[x + m][y + n][k] > max) {
//                                max = tensor.data[x + m][y + n][k];
//                                h = x +m;
//                                w = y + n;
//                            }
//                        }
//                    }
//                    result.data[h][w][k] = max;
//                }
//            }
//        }
//        System.out.println(result.print());
//    }
}