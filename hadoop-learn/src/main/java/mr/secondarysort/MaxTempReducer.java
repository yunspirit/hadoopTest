package mr.secondarysort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer
 */
public class MaxTempReducer extends Reducer<ComboKey, NullWritable, IntWritable, IntWritable>{

    /**
     *  进入reduce时候，已经是二次排序后
     *
     *  同一个组内的key是变化的，因为设计year相同就放到同一组，
     *
     *  同一组内的数据执行reduce
     */
    @Override
    protected void reduce(ComboKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int year = key.getYear();
        int temp = key.getTemp();
        System.out.println("==============>reduce");
        for(NullWritable v : values){
            System.out.println(key.getYear() + " : " + key.getTemp());
        }
        context.write(new IntWritable(year),new IntWritable(temp));
    }
}
