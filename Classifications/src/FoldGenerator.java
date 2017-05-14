import java.util.ArrayList;

/**
 * Function:
 * 1. Takes in pima.txt and converts to pima-folds.txt
 * 2. Used by class Algorithm Accuracy to test for the percentage accuracy of each algorithm
 * Input: filename
 * Output: ArrayList<ArrayList<double[]>>
 */

public class FoldGenerator {
//    ArrayList<ArrayList<double[]>> fold_all = null;

    public static void main(String[] args) {
        String filename = "pima.txt";
        DataReader parser = new DataReader(filename);
        ArrayList<double[]> data = parser.parseFile();
        //create two classes containing all the data points with yes and no
        ArrayList<double[]> classY = new ArrayList<double[]>();
        ArrayList<double[]> classN = new ArrayList<double[]>();
        int yes = 268;
        int no = 500;

        for(double[] entry: data) {
            if(entry[entry.length-1] == 1) {
                classY.add(entry); //add it to the class with yes's
            } else {
                classN.add(entry); //add it to the class with no's
            }
        }

        double yesPercent = (double)268/(double)768;
//        double noPercent = 500/768;

        //10-fold
        int baseEntries = data.size() / 10;
//        int remainder = data.size() % 10;

        ArrayList<double[]> fold = null;

        int Yptr = 0;
        int Nptr = 0;
        ArrayList<ArrayList<double[]>> fold_all = new ArrayList<ArrayList<double[]>>(10);
        for (int k = 0; k < 10; k++) {
            fold = new ArrayList<>(100);
            for (int i = 0; i < baseEntries; i++) {
                if (i < (int) (yesPercent * baseEntries)) {
                    fold.add(classY.get(Yptr));
                    Yptr++;
                } else {
                    fold.add(classN.get(Nptr));
                    Nptr++;
                }
            }
            fold_all.add(fold);
        }

        //deal with remainders
        int it = 0;
        while(!classY.isEmpty() && Yptr < classY.size()) {
            fold_all.get(it).add(classY.get(Yptr));
            it++;
            Yptr++;
        }

//        testing: stratification of folds
//        int nyes = 0;
//        int nnos = 0;
//        for(ArrayList<double[]> da : fold_all) {
//            for(double[] dd : da) {
//                if(dd[dd.length-1] == 1) {
//                    nyes++;
//                } else {
//                    nnos++;
//                }
//            }
//            System.out.println("nyes = " + nyes);
//            System.out.println("nnos = " + nnos);
//            nyes = 0;
//            nnos = 0;
//        }

    }
}