/**
 * Created by Ben on 16/05/2017.
 */
public class Entry implements Comparable<Entry> {

    int m_class;
    double m_diff;

    Entry(double diff, int class_no) {
        m_class = class_no;
        m_diff = diff;
    }

    void setDiff(double diff) {
        this.m_diff = diff;
    }

    double getDiff() {
        return this.m_diff;
    }

//    void setClass(int num) {
//        this.m_class = num;
//    }

    int getClassNo() {
        return this.m_class;
    }

    @Override
    public int compareTo(Entry o) {
        return Double.compare(m_diff, o.getDiff());
    }
}
