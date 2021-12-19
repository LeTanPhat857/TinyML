package tinyML.net.weightInit;

public class WeightInit {

    public static iWeightInit get(String type) {
        if (type.equalsIgnoreCase("randomWeight")) {
            return new RandomWeight();
        }
        return null;
    }
}
