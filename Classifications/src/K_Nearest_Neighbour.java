import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Takes in a training set and a testing set
 * Runs the K-Nearest-Neighbour algorithm on it
 * Stores the accuracy
 * Stores the predicted yes/no
 * Input: ArrayList<ArrayList<double[]>>
 * Output: String[]
 */
public class K_Nearest_Neighbour {

    ArrayList<double[]> training;
    ArrayList<double[]> testing;
    int k;

    public K_Nearest_Neighbour(ArrayList<double[]> training, ArrayList<double[]> testing, int k) {
        this.training = training;
        this.testing = testing;
        this.k = k;
    }

    
    //Returns majority class
    private String getMajority(List<Entry> diffs, int k) {
		int yes = 0;
		int no = 0; 
		for (int i = 0; i < k; i++) {
			if (diffs.get(i).getClassNo() == 1)
				yes++;
			else no++; 
		}
		
		//if tie choose yes
		if (yes >= no)
			return "Yes";
		else 
			return "No"; 
		
	}
	
    
    //Returns the distance from an example to training entry 
    private double get_distance(double[] test, double[] train) {
		double first;  
		double second; 
		double diff = 0; 
		for (int i = 0; i <test.length; i++) {
			first = test[i];
			second = train[i];
			diff +=Math.pow(first-second, 2);
		}
		
		
		return Math.sqrt(diff);
		
	}
    
    
    //The proper k_neighbour algorithm. Gets the majority class for a single example
    private String k_neighbour(double[] test, List<double[]> training, int k ) {
    	List<Entry> diffs = new ArrayList<Entry>(); 

    	for (int j = 0; j < training.size(); j++) {
			double[] train = training.get(j); 
			diffs.add(new Entry(get_distance(test, train), (int)train[8] ));
				
		}
	    Collections.sort(diffs, Comparator.comparing(Entry::getDiff));
	    return getMajority(diffs,k);  
    }
    
    
    
    
    
  //Returns list of majority class for all examples 
    public List<String> algorithm(List<double[]> testing, List<double[]> training, int k) {
  	
    	List<String> prediction = new ArrayList<String>(); 
    	
    	for (int i = 0; i < testing.size(); i++) {
    		double[] test = testing.get(i); 
    		prediction.add(k_neighbour(test, training,k));
    	
    	}
  
  
        return prediction;
    }
    
    }
