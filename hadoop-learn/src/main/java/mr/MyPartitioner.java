package mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区
 */
public class MyPartitioner extends Partitioner<Text, IntWritable>{

    //会打印在datanode的日志目录下，/hadoop/log/userlogs/ 这个目录专门存放用户打印的信息
    public MyPartitioner(){
        System.out.println("new MyPartitioner()");
    }



    //会造成数据倾斜
    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        System.out.println("MyPartitioner.getPartition()");
        return 0;
    }
}
