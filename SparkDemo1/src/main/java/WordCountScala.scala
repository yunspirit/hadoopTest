package com.it18zhang.spark.scala ;
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object WordCountScala {
    def main(args: Array[String]): Unit = {
        //创建Spark配置对象
        val conf = new SparkConf()
        conf.setAppName("WordCountScala")
        //设置master属性
        conf.setMaster("local") ;

        //通过conf创建sc
        val sc = new SparkContext(conf)

        //加载文本文件
        val rdd1 = sc.textFile("d:/scala/test.txt")
        //压扁
        val rdd2 = rdd1.flatMap(_.split(" ")) ;
        println(rdd2.count())
        //映射w => (w,1)
        val rdd3 = rdd2.map((_,2))

        //rdd3.countByKey().foreach(t => {println(t)})

        //按照key聚合，指定分区数
        rdd3.reduceByKey(_ + _,3).collect().foreach(println)
        //rdd4.saveAsTextFile("d:/scala/out");
        //rdd4.saveAsSequenceFile("d:/scala/seq")
    }
}
