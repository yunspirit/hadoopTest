package mr.skew1.Stage2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 解析数据倾斜问题
 *
 *  读取阶段1生成的 输出文件，解析文件，开始新的MR
 *
 * 解决方法1，自定义随机分区，让hello字符串分散在多个分区中
 *  *
 *  * 结果；生成的每个文件中都会统计hello，但是hello分散在各个文本中，结果不准确
 *  *
 *  * 如何？   继续使用MR处理生成的文件，再做一次统计
 *
 */
public class WCSkewApp2 {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("WCSkewApp2");                        //作业名称
        job.setJarByClass(WCSkewApp2.class);                 //搜索类
        job.setInputFormatClass(TextInputFormat.class);     //设置输入格式

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00000"));
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00001"));
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00002"));
        FileInputFormat.addInputPath(job,new Path("d:/mr/skew/out/part-r-00003"));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path("d:/mr/skew/out2"));

        job.setMapperClass(WCSkewMapper2.class);             //mapper类
        job.setReducerClass(WCSkewReducer2.class);           //reducer类

        job.setNumReduceTasks(4);                       //reduce个数

        job.setMapOutputKeyClass(Text.class);           //
        job.setMapOutputValueClass(IntWritable.class);  //

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);     //

        job.waitForCompletion(true);
    }
}
