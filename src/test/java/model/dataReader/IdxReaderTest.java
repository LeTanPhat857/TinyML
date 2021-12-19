package model.dataReader;

import tinyML.dataReader.IdxReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IdxReaderTest {

    @Test
    void loadTrainingData() {
        IdxReader idxReader = new IdxReader();
        boolean checker = idxReader.loadTrainingData("src/main/java/data/MNIST/train-images.idx3-ubyte");
        assertTrue(checker);
    }

    @Test
    void loadTrainingLabel() {
        System.out.println(26 / 255.0);
    }

    @Test
    void loadTestingData() {
    }

    @Test
    void loadTestingLabel() {
    }
}