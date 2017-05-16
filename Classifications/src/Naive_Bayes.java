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

    //code to split into two array list by class
//    ArrayList<double[]> classY = new ArrayList<double[]>();
//    ArrayList<double[]> classN = new ArrayList<double[]>();
//    int yes = 0;
//    int no = 0;
//
//        for(double[] entry: data) {
//        if(entry[entry.length-1] == 1) {
//            yes++;
//            classY.add(entry); //add it to the class with yes's
//        } else {
//            no++;
//            classN.add(entry); //add it to the class with no's
//        }
//    }
}
