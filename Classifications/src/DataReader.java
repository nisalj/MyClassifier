import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Ben on 13/05/2017.
 */
public class DataReader {
    public static void main(String[] args) throws IOException {
//        System.out.println(new File(".").getAbsoluteFile());
        Scanner file = new Scanner(new File("pima.txt"));
        String[] split;
        split = file.nextLine().split(",");

        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }

//        while(file.hasNext()) {
//
//
//        }
    }
}
