package tinyML.dataReader;

import tinyML.dataType.Matrix;

import java.io.*;

public class IdxReader implements iDataReader {
    // fields
    private boolean normalization;
    private int numOfClass;

    private int numOfTrainImgs;
    private int numOfTestImgs;
    private int numOfRows;
    private int numOfCols;

    private Matrix trainingData, trainingLabel, testingData, testingLabel;

    // methods
    @Override
    public iDataReader setNormalization(boolean bool) {
        this.normalization = bool;
        return this;
    }

    @Override
    public boolean getNormalization() {
        return this.normalization;
    }

    @Override
    public Matrix getTrainingData() {
        return this.trainingData;
    }

    @Override
    public Matrix getTrainingLabel() {
        return this.trainingLabel;
    }

    @Override
    public Matrix getTestingData() {
        return this.testingData;
    }

    @Override
    public Matrix getTestingLabel() {
        return this.testingLabel;
    }

    @Override
    public boolean loadTrainingData(String dataPath) {
        if ((this.trainingData = this.readTrainingData(dataPath)) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean loadTrainingLabel(String labelPath) {
        if ((this.trainingLabel = this.readLabel(labelPath)) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean loadTestingData(String dataPath) {
        if ((this.testingData = this.readTestingData(dataPath)) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean loadTestingLabel(String labelPath) {
        if ((this.testingLabel = this.readLabel(labelPath)) != null) {
            return true;
        }
        return false;
    }

    private DataInputStream createDIS(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                throw new Exception("File khong ton tai!");
            }
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int magicNumber = dataInputStream.readInt();
//            if (magicNumber != 0x803 || magicNumber != 0x801) {
//                dataInputStream.close();
//                throw new Exception("Magic number khong dung!");
//            }
            return dataInputStream;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private Matrix readTrainingData(String path) {
        try {
            DataInputStream dataInputStream = createDIS(path);
            this.numOfTrainImgs = dataInputStream.readInt();
            this.numOfRows = dataInputStream.readInt();
            this.numOfCols = dataInputStream.readInt();
            Matrix result = Matrix.create(this.numOfTrainImgs, this.numOfRows * this.numOfCols);
            for (int n = 0; n < numOfTrainImgs; n++) {
                result.data[n] = this.normalize(this.read(dataInputStream, numOfRows * numOfCols));
            }
            dataInputStream.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Matrix readTestingData(String path) {
        try {
            DataInputStream dataInputStream = createDIS(path);
            this.numOfTestImgs = dataInputStream.readInt();
            this.numOfRows = dataInputStream.readInt();
            this.numOfCols = dataInputStream.readInt();
            Matrix result = Matrix.create(numOfTestImgs, numOfRows*numOfCols);
            for (int n = 0; n < numOfTestImgs; n++) {
                result.data[n] = this.normalize(this.read(dataInputStream, numOfRows * numOfCols));
            }
            dataInputStream.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Matrix readLabel(String path) {
        try {
            DataInputStream dataInputStream = createDIS(path);
            int numberOfExample = dataInputStream.readInt();
            Matrix result = Matrix.create(numberOfExample, numOfClass);
            for (int n = 0; n < numberOfExample; n++) {
                byte label = dataInputStream.readByte();
                result.data[n][label % numOfClass] = 1;
            }
            dataInputStream.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private byte[] read(InputStream inputStream, int length) {
        try {
            byte[] data = new byte[length];
            int offset = 0;

            while (true) {
                int read = inputStream.read(data, offset, data.length - offset);

                if (read < 0) {
                    break;
                }

                offset += read;

                if (offset == data.length) {
                    return data;
                }
            }
            throw new IOException("Tried to read " + data.length + " bytes, but only found " + offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double[] normalize(byte[] data) {
        double result[] = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            if (this.normalization) {
                result[i] = Byte.toUnsignedInt(data[i]) / 255.0;
            } else {
                result[i] = Byte.toUnsignedInt(data[i]);
            }
        }
        return result;
    }

    @Override
    public int getNumOfTrainImgs() {
        return numOfTrainImgs;
    }

    @Override
    public int getNumOfTestImgs() {
        return numOfTestImgs;
    }

    @Override
    public int getNumOfRows() {
        return numOfRows;
    }

    @Override
    public int getNumOfCols() {
        return numOfCols;
    }

    @Override
    public int getNumOfClass() {
        return numOfClass;
    }

    @Override
    public iDataReader setNumOfClass(int numOfClass) {
        this.numOfClass = numOfClass;
        return this;
    }
}
