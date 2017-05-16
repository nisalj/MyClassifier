import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Ben on 16/05/2017.
 */
public class EntryTest {
    @Test
    public void test1() {
        Random r = new Random();
        ArrayList<Entry> test = new ArrayList<>();
        test.add(new Entry(0.1, 0));
        test.add(new Entry(0.3, 1));
        test.add(new Entry(0.2, 0));
        for (int i = 0; i < 1000; i++) {
            test.add(new Entry(10*r.nextDouble(), 0));
        }
        Collections.sort(test);
        for (Entry itr:test) {
            System.out.println(itr.getDiff());
        }
    }
}