import java.util.*;

class Entry  {
	int m_class; 
	double m_diff; 
	
	Entry(double diff, int class_no) {
		m_class = class_no;
		m_diff = diff; 
	}
	
	void setDiff(double diff) {
		this.m_diff = diff; 
	}
	
	double getDiff() {
		return this.m_diff; 
	}
	
	void setClass(int num) {
		this.m_class = num; 
	}
	
	int getClassNo() {
		return this.m_class; 
	}
	

	
 }


public class MyClassifier {

	
	
	
	
	//returns the difference between 2 entries 
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
	
	private int getMajority(List<Entry> diffs, int k) {
		int yes = 0;
		int no = 0; 
		for (int i = 0; i < k; i++) {
			if (diffs.get(i).getClassNo() == 1)
				yes++;
			else no++; 
		}
		
		//if tie choose yes
		if (yes >= no)
			return yes;
		else 
			return no; 
		
	}
	
	


	/* testing 1 fold - list of arrays
	training 9 folds - list of list of arrays
	*/
	
	public double k_nearest(List<double[]> testing, List<List<double[]>> training, int k) {
		int correct = 0; 
		int comparisons = 0; 
		List<Entry> diffs = new ArrayList<Entry>(); 
		
		
		for (int i = 0; i < testing.size(); i++) {
			// testing fold entry 
			double[] test = testing.get(i); 
			//training fold no
			for (int j = 0; i < training.size(); i++) {
				//training fold entry
				for (int z = 0; z < training.get(j).size(); z++) {
					//add the different distances from testing to each training 
				double [] train = training.get(j).get(z); 
				diffs.add(new Entry(get_distance(test, train), (int)train[8] )); 
				}
			
			  
			}
			//sort difference 
		    Collections.sort(diffs, Comparator.comparing(Entry::getDiff));
		    //select closest k differences and their majority class
		    if ((int)test[8] == getMajority(diffs, k)) {
		    	correct++; 
		    }
		    comparisons++; 
		    diffs.removeAll(diffs); 
		    
		    //repeat for next test entry 

		}
		
		
		return correct/comparisons; 
	}


	
	

}
