package mr.skew1.Stage2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WCTextMapper
 */
public class WCSkewMapper2 extends Mapper<LongWritable, Text, Text, IntWritable>{

    public WCSkewMapper2(){
        System.out.println("new MaxTempMapper()");
    }

    /**
     * @Description  文本一行 使用 制表符 作为分割
     * @param key
     * @param value
     * @param context
     * @return void
    **/
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split("\t");
        context.write(new Text(arr[0]),new IntWritable(Integer.parseInt(arr[1])));
    }
}
