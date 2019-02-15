package mr.skew1.stage3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WCTextMapper
 */
public class WCSkewMapper3 extends Mapper<Text, Text, Text, IntWritable>{

    //每一行是字符串+字符串出现次数（是String，不是int）
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        context.write(key,new IntWritable(Integer.parseInt(value.toString())));
    }
}
