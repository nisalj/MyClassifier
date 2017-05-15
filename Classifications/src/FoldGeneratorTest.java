import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ben on 15/05/2017.
 */
public class FoldGeneratorTest {

    @Test
    public void testgen() throws Exception {
        String filename = "pima.txt";
        DataReader parser = new DataReader(filename);
        ArrayList<double[]> tester = parser.parseFile();
        FoldGenerator pima_folds = new FoldGenerator(tester);
        ArrayList<ArrayList<double[]>> result = pima_folds.fgen();

//        testing: stratification of folds
//        int nyes = 0;
//        int nnos = 0;
//        for(ArrayList<double[]> da : result) {
//            for (double[] dd : da) {
//                if (dd[dd.length - 1] == 1) {
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

//        int it = 1;
//        for (ArrayList<double[]> foldItr:result) {
//            System.out.println("fold" + it);
//            for (double[] entry:foldItr) {
//                for (int i = 0; i < entry.length; i++) {
//                    if (i == entry.length-1) {
//                        if (entry[i] == 1) { //if yes
//                            System.out.print("yes");
//                        } else { //if no
//                            System.out.print("no");
//                        }
//                    } else {
//                        System.out.print(entry[i] + ",");
//                    }
//
//                } System.out.println();
//            } System.out.println(); it++;
//        } System.out.close();
    }

}