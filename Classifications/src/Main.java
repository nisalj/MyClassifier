import java.util.ArrayList;

/**
 * Created by Ben on 15/05/2017.
 */
public class Main {

    public static void main(String[] args) {
        String testing_file = args[0];
        String training_file = args[1];
        String algorithm_type = args[2];

        // TODO: 15/05/2017 Read in algorithm type, need variable kval
        int kValue = 0;

        DataReader parser1 = new DataReader(testing_file);
        ArrayList<double[]> testing_set = parser1.parseFile();

        DataReader parser2 = new DataReader(training_file);
        ArrayList<double[]> training_set = parser2.parseFile();

        FoldGenerator folder = new FoldGenerator(training_set);
        ArrayList<ArrayList<double[]>> fold = folder.fgen();

        K_Nearest_Neighbour nn = new K_Nearest_Neighbour();
        for (int i = 0; i < 10; i++) {
            String[] result = nn.algorithm(fold.get(i), testing_set, kValue);
        }

    }
}
