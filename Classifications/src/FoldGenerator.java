import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Function:
 * 1. Takes in pima.txt and converts to pima-folds.txt
 * 2. Used by class Algorithm Accuracy to test for the percentage accuracy of each algorithm
 * 3. Creates file pima-folds.csv to be used in report
 * Input: ArrayList<double[]>
 * Output: ArrayList<ArrayList<double[]>> 
 */

public class FoldGenerator {
    private ArrayList<ArrayList<double[]>> all_folds = null;
    private ArrayList<double[]> data = null;

    public FoldGenerator(ArrayList<double[]> data) {
        this.data = data;
    }

    public ArrayList<ArrayList<double[]>> fgen() {
//        String filename = "pima.txt";
//        DataReader parser = new DataReader(filename);
//        ArrayList<double[]> data = parser.parseFile();
        //create two classes containing all the data points with yes and no
        ArrayList<double[]> classY = new ArrayList<double[]>();
        ArrayList<double[]> classN = new ArrayList<double[]>();
        int yes = 0;
        int no = 0;

        for(double[] entry: data) {
            if(entry[entry.length-1] == 1) {
                yes++;
                classY.add(entry); //add it to the class with yes's
            } else {
                no++;
                classN.add(entry); //add it to the class with no's
            }
        }

        double yesPercent = (double)yes/(double)(yes+no);
//        double noPercent = 500/768;

        //10-fold
        int baseEntries = data.size() / 10;
//        int remainder = data.size() % 10;

        ArrayList<double[]> one_fold = null;

        int Yptr = 0;
        int Nptr = 0;

        all_folds = new ArrayList<ArrayList<double[]>>(10);
        for (int k = 0; k < 10; k++) {
            one_fold = new ArrayList<>(100);
            for (int i = 0; i < baseEntries; i++) {
                if (i < (int) (yesPercent * baseEntries)) {
                    one_fold.add(classY.get(Yptr));
                    Yptr++;
                } else {
                    one_fold.add(classN.get(Nptr));
                    Nptr++;
                }
            }
            all_folds.add(one_fold);
        }

        //deal with remainders
        int it = 0;
        while (!classY.isEmpty() && Yptr < classY.size()) {
            all_folds.get(it).add(classY.get(Yptr));
            it++;
            Yptr++;
        } it = 1;
        String out = "pima-folds.txt";
        PrintWriter printer = null;
        try {
            printer = new PrintWriter(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert printer != null;
        for (ArrayList<double[]> foldItr:all_folds) {
            printer.println("fold" + it);
            for (double[] entry:foldItr) {
                for (int i = 0; i < entry.length; i++) {
                    if (i == entry.length-1) {
                        if (entry[i] == 1) { //if yes
                            printer.print("yes");
                        } else { //if no
                            printer.print("no");
                        }
                    } else {
                        printer.print(entry[i] + ",");
                    }
                } printer.println();
            } printer.println(); it++;
        } printer.close();

    return all_folds;
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