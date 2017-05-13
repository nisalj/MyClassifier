import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class DataReader {
    public static void main(String args[]) throws IOException {

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

        //create two classes containing all the data points with yes and no
        List<double[]> classY = new ArrayList<double[]>();
        List<double[]> classN = new ArrayList<double[]>();
        int yes = 0;
        int no = 0;

        for (int i = 0; i < data.size(); i++) {
            //get the last attribute of the entry, which is the class type
            if (data.get(i)[data.get(0).length-1] == 1) {
                yes += 1;
                classY.add(data.get(i)); //add it to the class with yes's
            } else {
                no += 1;
                classN.add(data.get(i)); //add it to the class with no's
            }
        }

        double fractionY = (double)yes/(double)data.size();
        double fractionN = (double)no/(double)data.size();

        //10-fold
        int baseEntries = data.size()/10;
        int remainder = data.size()%10;

        List<double[]> fold = new ArrayList<double[]>();
        int numY = (int)(fractionY*baseEntries);
        int numN = (int)(fractionN*baseEntries);

        int Yptr = 0;
        int Nptr = 0;


        for (int i = 0; i < baseEntries ; i++) {
            if (i < (int)(fractionY*baseEntries)) {
                fold.add(classY.get(Yptr));
                Yptr++;
            } else {
                fold.add(classN.get(Nptr));
                Nptr++;
            }
        }



        int numyes = 0;
        int numno = 0;
        for (int i = 0; i < fold.size(); i++) {
            if(fold.get(i)[8] == 1) {
                numyes++;
            } else {
                numno++;
            }
        }

        System.out.println(baseEntries);
//        System.out.println(remainder);
        System.out.println(numyes);
        System.out.println(numno);
//        System.out.println(fractionY);
//        System.out.println(fractionN);


        //testing: number of rows
//        System.out.println(data.size());

        //testing: reads in a row
//        for (int i = 0; i < 9; i++) {
//            System.out.println(data.get(3)[i]);
//        }
    }
}
