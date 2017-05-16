import java.util.ArrayList;

/**
 * Takes in a training set and a testing set
 * Runs the Naive-Bayes algorithm on it
 * Stores the accuracy
 * Stores the predicted yes/no
 */
public class Naive_Bayes {
    private static Naive_Bayes instance = new Naive_Bayes() ;
	
	 ArrayList<double[]> classY = null;
	 ArrayList<double[]> classN = null;
	 ArrayList<Double> classYMean = null;
	 ArrayList<Double> classNMean = null;
	 ArrayList<Double> classYstd = null;
	 ArrayList<Double> classNstd = null;
	 double probYes;
	 double probNo; 
	 
	 
	 public  Naive_Bayes() {
		 if (instance == null) {
			 this.classY = new ArrayList<double[]>();
			 this.classN = new ArrayList<double[]>();
			 this.classYMean = new ArrayList<Double>();
			 this.classNMean = new ArrayList<Double>();
			 this.classYstd = new ArrayList<Double>();
			 this.classNstd = new ArrayList<Double>();
			 this.probNo = 0;
			 this.probYes = 0;
		 } else
			 return;
		 
	 }
	 
	 private static void clearAll() {
		 instance.classY.clear(); 
		 instance.classN.clear(); 
		 instance.classYMean.clear();; 
		 instance.classNMean.clear(); 
		 instance.classYstd.clear(); 
		 instance.classNstd.clear(); 
		 instance.probYes = 0;
		 instance.probNo = 0; 
	 }
	 
	 
	
	 
	
	private static void setLists(ArrayList<double[]> training) {
		clearAll();
		int attributes = training.get(0).length-1;
		  
		int yes = 0;
		int no = 0;
		        for(double[] entry: training) {
		        if(entry[entry.length-1] == 1) {
		            yes++;
		            instance.classY.add(entry); //add it to the class with yes's
		        } else {
		            no++;
		            instance.classN.add(entry); //add it to the class with no's
		        }
		       }
		        
		  instance.probYes = yes/(yes+no);
		  instance.probNo = no/(yes+no);
		  
		  double sum = 0;
		  double std = 0; 
		  double mean = 0;
		  for (int i = 0; i < attributes ;i++ )  {
			  
			  for (double[] entry: instance.classY) {
				  sum += entry[i];
			  }
			  mean = sum/yes;
			  instance.classYMean.add(mean);
			  for (double[] entry: instance.classY) {
				  std += Math.pow(entry[i] - mean,2);  
			  }
			  std /= yes -1; 
			  std = Math.sqrt(std); 
			  instance.classYstd.add(std);
			  sum = 0; 
			  std = 0; 
			  
			  for (double[] entry: instance.classN) {
				  sum += entry[i];
			  }
			  mean = sum/no;
			  instance.classNMean.add(mean);
			  for (double[] entry: instance.classN) {
				  std += Math.pow(entry[i] - mean,2);  
			  }
			  std /= no -1; 
			  std = Math.sqrt(std); 
			  instance.classNstd.add(std);
			  
			  sum = 0; 
			  std = 0; 
			 
			  
		  }
		 
		     
		         
	}
	
	//Calculates P(attribute|yes) or P(attribute|no) value for an attribute
	private double  prob_density(double val, int attribute, int num) {
		double std = 0;
		double mean = 0;
		if (num == 1) {
			mean = instance.classYMean.get(attribute);
			std = instance.classYstd.get(attribute);
		}else {
			mean = instance.classNMean.get(attribute);
			std = instance.classNstd.get(attribute);
		}
		double mult = 1/(2*Math.PI*std); 
		double exp = (-1*Math.pow(val-mean, 2))/(Math.pow(std,2)*2);
		return mult*Math.pow(Math.E, exp); 
		
	}
	
	
	//Returns majority class for one entry
	private String bayes(double[] testing, ArrayList<double[]> training) {
		double yesVal = 0;
		double noVal = 0; 
		for (int i = 0; i <testing.length-1; i++) {
			yesVal *= prob_density(testing[i],i,1); 	
			noVal  *= prob_density(testing[i],i,0);
		}
		yesVal *= instance.probYes;
		noVal *= instance.probNo; 
		
		if (yesVal >= noVal)
			return "yes";
		else 
			return "no";
		
		
	}
	

	//Returns majority classes for all entries
    public String[] algorithm(ArrayList<double[]> training, ArrayList<double[]> testing) {
    	setLists(training);   	
        String[] prediction = new String[testing.size()];
        for (int i = 0; i < testing.size(); i++) {
        	prediction[i] = bayes(testing.get(i), training);
        }
        
        return prediction;
          
        
    }
    

}
