package mr.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 *ComboKeyComparator
 *   明明Combkokey自己就可以比较，还需要自己写Comparator？
 */
public class ComboKeyComparator extends WritableComparator {

    protected ComboKeyComparator() {
        super(ComboKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ComboKey k1 = (ComboKey) a;
        ComboKey k2 = (ComboKey) b;
        return k1.compareTo(k2);
    }
}