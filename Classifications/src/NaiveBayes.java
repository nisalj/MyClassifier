import java.util.ArrayList;

import static java.lang.Math.sqrt;

/**
 * Takes in a training set and a testing set
 * Runs the Naive-Bayes algorithm on it
 * Stores the accuracy
 * Stores the predicted yes/no
 */
public class NaiveBayes {

    ArrayList<double[]> classY = null;
    ArrayList<double[]> classN = null;
    ArrayList<Double> classYMean = null;
    ArrayList<Double> classNMean = null;
    ArrayList<Double> classYstd = null;
    ArrayList<Double> classNstd = null;
    double probYes;
    double probNo;


    public  NaiveBayes() {
        this.classY = new ArrayList<double[]>();
        this.classN = new ArrayList<double[]>();
        this.classYMean = new ArrayList<Double>();
        this.classNMean = new ArrayList<Double>();
        this.classYstd = new ArrayList<Double>();
        this.classNstd = new ArrayList<Double>();
        this.probNo = 0;
        this.probYes = 0;
    }

    //Returns majority classes for all entries
    public String[] algorithm(ArrayList<double[]> training, ArrayList<double[]> testing) {
        setLists(training);
        String[] prediction = new String[testing.size()];
        for (int i = 0; i < testing.size(); i++) {
            prediction[i] = bayes(testing.get(i));
        }
        return prediction;
    }

    private void setLists(ArrayList<double[]> training) {

        int attributes = training.get(0).length-1;

        int yes = 0;
        int no = 0;
        for(double[] entry: training) {
            if (entry[entry.length-1] == 1) {
                yes++;
                classY.add(entry); //add it to the class with yes's
            } else if (entry[entry.length-1] == 0){
                no++;
                classN.add(entry); //add it to the class with no's
            } else {
                System.out.println("--------------------Error-------------------");
                System.out.println("Failed to add entry");
            }
        }

        probYes = (double)yes/(double)(yes+no);
        probNo = (double)no/(double)(yes+no);

        double sum = 0;
        double std = 0;
        double mean = 0;
        for (int i = 0; i < attributes ;i++ )  {

            for (int k = 0; k < classY.size(); k++) {
                sum += classY.get(k)[i];
            }
            mean = sum/yes;
            classYMean.add(mean);
            for (int k = 0; k < classY.size(); k++) {
                std += Math.pow(classY.get(k)[i] - mean,2);
            }
            std /= yes - 1;
            std = sqrt(std);
            classYstd.add(std);
            sum = 0;
            std = 0;

            for (int k = 0; k < classN.size(); k++) {
                sum += classN.get(k)[i];
            }
            mean = sum/no;
            classNMean.add(mean);
            for (int k = 0; k < classN.size(); k++) {
                std += Math.pow(classN.get(k)[i] - mean,2);
            }
            std /= no -1;
            std = sqrt(std);
            classNstd.add(std);

            sum = 0;
            std = 0;
        }
    }

    //Calculates P(attribute|yes) or P(attribute|no) value for an attribute
    private double  prob_density(double val, int attribute, int num) {
        double std = 0;
        double mean = 0;
        if (num == 1) {
            mean = classYMean.get(attribute);
            std = classYstd.get(attribute);
        }else {
            mean = classNMean.get(attribute);
            std = classNstd.get(attribute);
        }
        double mult = 1/(std*sqrt(2*Math.PI));
        double exp = (-1*Math.pow(val-mean, 2))/(Math.pow(std,2)*2);
        return mult*Math.pow(Math.E, exp);

    }


    //Returns majority class for one entry
    private String bayes(double[] testing) {
        double yesVal = 1; //changed from 0 to 1
        double noVal = 1; //changed from 0 to 1
        for (int i = 0; i <testing.length; i++) {
            yesVal *= prob_density(testing[i],i,1);
            noVal  *= prob_density(testing[i],i,0);
        }
        yesVal = probYes*yesVal;
        noVal = probNo*noVal;

        if (yesVal >= noVal)
            return "yes";
        else
            return "no";
    }

}
