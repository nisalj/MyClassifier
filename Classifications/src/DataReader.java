import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Ben on 13/05/2017.
 */
public class DataReader {
    List<double[]> entries = new ArrayList<>();
    public List<double[]> returnsData(String[] args) throws IOException {

        Scanner file = new Scanner(new File("pima.txt"));

        List<double[]> data = new ArrayList<double[]>();

        while(file.hasNext()) {
            String[] split = file.nextLine().split(",");
            double[] singleRow = new double[split.length];

            //cast from string to double
            for (int i = 0; i < split.length - 1; i++) {
                singleRow[i] = Double.parseDouble(split[i]);
            }

            //convert class type to double
            //1 for yes, 0 for no
            if (split[split.length-1].equals("yes")) {
                singleRow[split.length-1] = 1;
            } else {
                singleRow[split.length-1] = 0;
            }

            //add to our list of doubles
            data.add(singleRow);
        }

        return data;
        //create two classes containing all the data points with yes and no
//        Queue<double[]> classY = new Queue<double[]>();
//        List<double[]> classN = new ArrayList<double[]>();
//        int yes = 0;
//        int no = 0;
//        for (int i = 0; i < data.size(); i++) {
//            //get the last attribute of the entry, which is the class type
//            if (data.get(i)[data.get(0).length-1] == 1) {
//                yes += 1;
//                classY.add(data.get(i)); //add it to the class with yes's
//            } else {
//                no += 1;
//                classN.add(data.get(i)); //add it to the class with no's
//            }
//        }
//
//        double fractionY = (double)yes/(double)data.size();
//        double fractionN = (double)no/(double)data.size();
//
//        //10-fold
//        int baseEntries = data.size()/10;
//        int remainder = data.size()%10;
//
//        List<double[]> fold = new ArrayList<double[]>();
//        int numY = (int)(fractionY*baseEntries);
//        int numN = (int)(fractionN*baseEntries);
//        for (int i = 0; i <= baseEntries ; i++) {
//            fold.add(classY.get(i));
//        }
//        System.out.println(baseEntries);
//        System.out.println(remainder);
//        System.out.println(numY);
//        System.out.println(numN);
//        System.out.println(fractionY);
//        System.out.println(fractionN);


//        testing: number of rows
//        System.out.println(data.size());

//        testing: reads in a row
//        for (int i = 0; i < 9; i++) {
//            System.out.println(data.get(3)[i]);
//        }
    }
}
