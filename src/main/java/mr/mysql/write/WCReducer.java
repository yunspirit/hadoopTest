package mr.mysql.write;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * WCReducer
 *
 */
public class WCReducer extends Reducer<Text,IntWritable,MyDBWritable,NullWritable> {

    //单词是key，value是单词出现次数
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0 ;
        for(IntWritable w : values){
            count = count + w.get() ;
        }
        MyDBWritable keyOut = new MyDBWritable();
        keyOut.setWord(key.toString());
        keyOut.setWordCount(count);
        context.write(keyOut,NullWritable.get());
    }
}
