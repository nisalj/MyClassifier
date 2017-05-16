import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Function: Takes in a .csv file and converts entries into a list of double arrays
 * Input: String (Name of the file to read in)
 * Output: ArrayList<double[]> (An array of entries converted to double values, with class yes to 1 and no to 0
 */

public class DataReader {

    //Instance variables
    private ArrayList<double[]> data = null;
    private String s = null;

    //Constructor
    public DataReader(String s) {
        data = new ArrayList<>(100);
        this.s = s;
    }

    //Reads in file based on filename pass from constructor
    public ArrayList<double[]> parseFile() {

        Scanner file = null;
        try {
            file = new Scanner(new File(s));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(file.hasNext()) {
            String[] split = file.nextLine().split(",");
            double[] singleRow = new double[split.length];

            //convert class type to double
            //1 for yes, 0 for no
            if (split.length == 9) {
                for (int i = 0; i < split.length - 1; i++) {
                    singleRow[i] = Double.parseDouble(split[i]);
                }

                if (split[split.length-1].equals("yes")) {
                    singleRow[split.length-1] = 1;
                } else if (split[split.length-1].equals("no")) {
                    singleRow[split.length-1] = 0;
                } else {
                    System.out.println("+++++++++WRONG SPLITTING+++++++++");
                }
            } else {
                for (int i = 0; i < split.length; i++) {
                    singleRow[i] = Double.parseDouble(split[i]);
                }
            }

            //add to our list of doubles
            data.add(singleRow);
        }
        return data;
    }
}
