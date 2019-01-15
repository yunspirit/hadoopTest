package mr.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 按照年份进行分组对比器实现
 */
public class YearGroupComparator extends WritableComparator {

    protected YearGroupComparator() {
        super(ComboKey.class, true);
    }


    /**
     * @Description   //同一个分区内的key进行分组聚合，即使同一个分区内，也可能年份不同，对吧
     * @param a
     * @param b
     * @return int
    **/
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ComboKey k1 = (ComboKey)a ;
        ComboKey k2 = (ComboKey)b ;
        return k1.getYear() - k2.getYear() ;
    }
}
