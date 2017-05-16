import java.util.ArrayList;

/**
 * Takes in a training set and a testing set
 * Runs the Naive-Bayes algorithm on it
 * Stores the accuracy
 * Stores the predicted yes/no
 */
public class Naive_Bayes {
    ArrayList<double[]> testing = new ArrayList<>(100);
    ArrayList<double[]> training = new ArrayList<>(100);
    public Naive_Bayes(ArrayList<double[]> training, ArrayList<double[]> testing) {
        this.training = training;
        this.testing = testing;
    }

    public String[] algorithm() {
        String[] prediction = new String[testing.size()];
        return prediction;
    }
}
