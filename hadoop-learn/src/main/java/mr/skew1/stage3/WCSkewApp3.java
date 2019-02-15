package mr.skew1.stage3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *解析数据倾斜问题
 *
 * 处理阶段1产生的数据，使用KeyValueTextInputFormat.class
 *
 */
public class WCSkewApp3 {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("WCSkewApp3");                        //作业名称
        job.setJarByClass(WCSkewApp3.class);                 //搜索类
        job.setInputFormatClass(KeyValueTextInputFormat.class);     //设置输入格式

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00000"));
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00001"));
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00002"));
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00003"));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path("d:/mr/skew/out2"));

        job.setMapperClass(WCSkewMapper3.class);             //mapper类
        job.setReducerClass(WCSkewReducer3.class);           //reducer类

        job.setNumReduceTasks(4);                       //reduce个数

        job.setMapOutputKeyClass(Text.class);           //
        job.setMapOutputValueClass(IntWritable.class);  //

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);     //

        job.waitForCompletion(true);
    }
}
