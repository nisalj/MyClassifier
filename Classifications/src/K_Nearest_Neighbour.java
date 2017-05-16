import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Takes in a training set and a testing set
 * Runs the K-Nearest-Neighbour algorithm on it
 * Stores the accuracy
 * Stores the predicted yes/no
 * Input: ArrayList<ArrayList<double[]>>
 * Output: String[]
 */
public class K_Nearest_Neighbour {

	//Returns list of majorities
	public String[] algorithm(ArrayList<double[]> training, ArrayList<double[]> testing, int k) {

		String[] prediction = new String[testing.size()];

		for (int i = 0; i < testing.size(); i++) {
			prediction[i] = k_neighbour(testing.get(i), training, k);
		}

		return prediction;
	}

	//The proper k_neighbour algorithm. Gets the majority class for a single example
	private String k_neighbour(double[] test, ArrayList<double[]> training, int k ) {
		ArrayList<Entry> diffs = new ArrayList<Entry>();

		for (int j = 0; j < training.size(); j++) {
			double[] train = training.get(j);
			diffs.add(new Entry(get_distance(test, train), (int)train[8] ));
		}


//		Collections.sort(diffs, new Comparator<Entry>() {
//			@Override
//			public int compare(Entry p1, Entry p2) {
//				return Double.compare(p1.getDiff(), p2.getDiff());
//			}
//		});
		Collections.sort(diffs);

//				Collections.sort(diffs, Comparator.comparing(Entry::getDiff));
		for (Entry itr:diffs) {
			System.out.println(itr.getDiff());
		}
		return getMajority(diffs,k);
	}

    //Returns majority class
    private String getMajority(ArrayList<Entry> diffs, int k) {
		int yes = 0;
		int no = 0; 
		for (int i = 0; i < k; i++) {
			if (diffs.get(i).getClassNo() == 1)
				yes++;
			else no++; 
		}
		
		//if tie choose yes
		if (yes >= no)
			return "yes";
		else 
			return "no";
	}

    //Returns the distance from an example to training entry 
    private double get_distance(double[] test, double[] train) {
		double first;  
		double second; 
		double diff = 0; 
		for (int i = 0; i <test.length-1; i++) { //changed from test.length to test.length-1
			first = test[i];
			second = train[i];
			diff +=Math.pow(first-second, 2);
		}
		return Math.sqrt(diff);
	}

}
