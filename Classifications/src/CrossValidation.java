import java.util.ArrayList;

/**
 * Takes in Command Line Argument for type of algorithm
 * Prints to System.out the accuracy for that algorithm
 */
public class CrossValidation {

    public static void main(String[] args) {

        DataReader parser = new DataReader("pima.txt");
        ArrayList<double[]> entries = parser.parseFile();
        FoldGenerator folder = new FoldGenerator(entries);
        ArrayList<ArrayList<double[]>> fold = folder.fgen();

        double[] accuracy = new double[10];
        int kValue = 5;
        //10-fold
        for (int k = 0; k < 10; k++) {

            //Adding the testing set
            ArrayList<double[]> testing = new ArrayList<>(100);
            testing.addAll(fold.get(k));

            //Training set is everything except the testing set
            ArrayList<double[]> training = new ArrayList<>(1000);
            for (int i = 0; i < 10; i++) {
                if (i != k) {
                    training.addAll(fold.get(i));
                }
            }

            //Calls the algorithm 10 times, each time with a difference testing and training set
            K_Nearest_Neighbour kNearest = new K_Nearest_Neighbour(training, testing, kValue);
            //Naive_Bayes nbtester = new Naive_Bayes(training, testing);
            //algorithm returns list 
            String[] result = kNearest.algorithm();
            String[] actual = new String[testing.size()];

            for (int i = 0; i < testing.size(); i++) {
                if (testing.get(i)[8] == 1) {
                    actual[i] = "yes";
                } else {
                    actual[i] = "no";
                }
            }

            int accurateEntry = 0;
            for (int i = 0; i < actual.length; i++) {
                if (result[i].equals(actual[i])) {
                    accurateEntry++;
                }
            }

            accuracy[k] = (double)accurateEntry/(double)actual.length;
        }


        double sum = 0;
        for (double d:accuracy) {
            sum += d;
        }

        double totalAccuracy = sum/10;

        System.out.println("totalAccuracy = " + totalAccuracy);


/*
        Compare result with testing set
        Find the accuracy
        for loop to iterate through both testing and result set, and increment when correct
        Take the average of the accuracies

        */

//        Take two sets of entries, compare frist entry from testing set to all entries of the training set
//        Update the distance, as well as the class
//        Sort by distance

    }
}
