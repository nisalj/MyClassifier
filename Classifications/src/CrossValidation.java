import java.util.ArrayList;

/**
 * Created by Ben on 15/05/2017.
 */
public class CrossValidation {
//    public CrossValidation(ArrayList<double[]> entries) {
//
//    }
    public static void main(String[] args) {
        DataReader parser = new DataReader("pima.txt");
        ArrayList<double[]> entries = parser.parseFile();
        FoldGenerator folder = new FoldGenerator(entries);
        ArrayList<ArrayList<double[]>> fold = folder.fgen();

        // fold2-fold10 as training, fold1 as testing
        ArrayList<double[]> training = new ArrayList<>(1000);
        for (int i = 1; i < 10; i++) {
            training.addAll(fold.get(i));
        }

        ArrayList<double[]> testing = new ArrayList<>(100);
        testing.addAll(fold.get(0));
        K_Nearest_Neighbour nearest5 = new K_Nearest_Neighbour(training, testing, 5);
    }
}
