import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test: DataReader
 */
public class DataReaderTest {
    @Test
    public void testParse() {
        DataReader parser = new DataReader("pima.txt");
        ArrayList<double[]> tester = parser.parseFile();
        for(double[] it:tester) {
            for(double dd:it) {
                System.out.printf(dd + " ");
            } System.out.println();
        }
    }
}