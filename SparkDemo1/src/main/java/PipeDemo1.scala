import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object PipeDemo1 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setAppName("WordCountScala")
        conf.setMaster("local[4]") ;

        val sc = new SparkContext(conf)

        var d = Array("file:///d:","file:///e:","file:///f:",3)
        val rdd = sc.parallelize(d)
        val rdd0 = rdd.pipe("ls ")
        rdd0.collect().foreach(println)
    }
}
