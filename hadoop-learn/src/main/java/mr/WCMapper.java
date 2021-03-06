package mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WCMapper
 */
public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    public WCMapper(){
        System.out.println("new MaxTempMapper()");
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyOut = new Text();
        IntWritable valueOut = new IntWritable();
        String[] arr = value.toString().split(" ");
        for(String s : arr){
            keyOut.set(s);
            valueOut.set(1);
            context.write(keyOut,valueOut);
            //执行一次就对计数器+1
            context.getCounter("m", Util.getInfo(this,"map")).increment(1);

        }
    }
}
