package tinyML.dataReader;

import tinyML.dataType.Matrix;

public interface iDataReader {

    public iDataReader setNormalization(boolean bool);

    public boolean getNormalization();

    public iDataReader setNumOfClass(int numOfClass);

    public int getNumOfClass();

    public int getNumOfTrainImgs();

    public int getNumOfTestImgs();

    public int getNumOfRows();

    public int getNumOfCols();

    public Matrix getTrainingData();

    public Matrix getTrainingLabel();

    public Matrix getTestingData();

    public Matrix getTestingLabel();

    public boolean loadTrainingData(String dataPath);

    public boolean loadTrainingLabel(String labelPath);

    public boolean loadTestingData(String dataPath);

    public boolean loadTestingLabel(String labelPath);
}
