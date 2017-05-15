import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Takes in a training set and a testing set
 * Runs the K-Nearest-Neighbour algorithm on it
 * Stores the accuracy
 * Stores the predicted yes/no
 * Input: ArrayList<ArrayList<double[]>>
 * Output: String[]
 */
public class K_Nearest_Neighbour {

//    Instance Variables
    ArrayList<double[]> training;
    ArrayList<double[]> testing;
    int k;

//    Constructor
    public K_Nearest_Neighbour(ArrayList<double[]> training, ArrayList<double[]> testing, int k) {
        this.training = training;
        this.testing = testing;
        this.k = k;
    }

//    Algorithm
    public String[] algorithm() {
        // TODO: 15/05/2017 runs the algorithm
        String[] prediction = null;


        return prediction;
    }
}
