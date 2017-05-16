import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Takes in two textfiles - one for the training set, one for the testing set
 * Input: Training Set(.txt), Testing Set(.txt), Algorithm-Type(String)
 * Output: yes/no(System.out) based on the predicted class
 * 1. Calls FoldGenerator
 * 2. Makes training set
 */

public class MyClassifier {

	public static void main(String[] args) {
		String training_file = args[0];
		String testing_file = args[1];
		String algorithm_type = args[2];

		// read in from textfile
		DataReader parser2 = new DataReader(training_file);
		ArrayList<double[]> training_set = parser2.parseFile();

		DataReader parser1 = new DataReader(testing_file);
		ArrayList<double[]> testing_set = parser1.parseFile();

		K_Nearest_Neighbour nn = new K_Nearest_Neighbour();
//		Naive_Bayes nb = new Naive_Bayes();

//		FoldGenerator folder = new FoldGenerator(training_set);
//		ArrayList<ArrayList<double[]>> fold = folder.fgen();

		// run algorithm based on input
		int kValue = 0;
		if (algorithm_type.charAt(0) == 'N') {
			System.out.println("N");
			// TODO: 16/05/2017 run the naive bayes algorithm
//			String[] result = nb.algorithm();
		} else {
			kValue = Character.getNumericValue(algorithm_type.charAt(0));
//			System.out.println("kValue = " + kValue);
			String[] result = nn.algorithm(training_set, testing_set, kValue);

			for (String class_type:result) {
				System.out.println(class_type);
			}

//			String log = "log.csv";
//			PrintWriter logger = null;
//			try {
//				logger = new PrintWriter(log);
//				for (String class_type:result) {
//					logger.print(class_type);
//					logger.println();
//				} logger.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//				System.out.println("File not found");
//			}


		}
	}
}
