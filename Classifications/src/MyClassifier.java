import java.io.*;
import java.util.Scanner;

public class MyClassifier {
    public static void main(String[] args) throws IOException {
        Scanner file = new Scanner(new File("pima.txt"));
        while(file.hasNext()) {
            String s = file.nextLine().trim();
        }
    }


}
