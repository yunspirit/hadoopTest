package mr.allsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

/**
 *  通过采样器 实现全排序
 */
public class MaxTempApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");

        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("MaxTempApp");                        //作业名称
        job.setJarByClass(MaxTempApp.class);                 //搜索类
        //本来是Text，key是偏移量LongWritable，value是一行文本
        //此处要改成sequenceFile，key是年份，value是温度，然后也要生成sequenceFile文件
        job.setInputFormatClass(SequenceFileInputFormat.class); //设置输入格式

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));


        //-----------------------------------------------------------------------------------------------






        //-----------------------------------------------------------------------------------------------


        job.setMapperClass(MaxTempMapper.class);             //mapper类
        job.setReducerClass(MaxTempReducer.class);           //reducer类


        job.setMapOutputKeyClass(IntWritable.class);        //
        job.setMapOutputValueClass(IntWritable.class);      //

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);         //



        //--------------------------------------------------------------------------------

        //创建随机采样器对象
        //freq: 每个key被选中的概率
        //numSapmple: 抽取样本的总数  一共要抽取多少个样本
        //maxSplitSampled: 最大采样切片数   采样要划分到多个区间
        //2个IntWritable是key、value值   key是年  value是温度值
        InputSampler.Sampler<IntWritable, IntWritable> sampler =
                new InputSampler.RandomSampler<IntWritable, IntWritable>(0.5, 3000, 3);

        job.setNumReduceTasks(3);                           //reduce个数

        //1、将sample数据写入分区文件.  之后TotalOrderPartitioner会读取这个分区文件
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),new Path("d:/mr/par.lst"));

        //2、设置全排序分区类
        job.setPartitionerClass(TotalOrderPartitioner.class);

        //此处可以debug进入  查看过程
        InputSampler.writePartitionFile(job, sampler);



        job.waitForCompletion(true);


    }
}