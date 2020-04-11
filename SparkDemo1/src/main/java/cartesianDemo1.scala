import org.apache.spark.{SparkConf, SparkContext}

/**
  *
  */
object cartesianDemo1 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setAppName("WordCountScala")
        conf.setMaster("local[4]") ;
        val sc = new SparkContext(conf)
        var d1 = Array("tom","tomas","tomasle","tomson")
        var d2 = Array("1234","3456","5678","7890")
        val rdd1 = sc.parallelize(d1)
        val rdd2 = sc.parallelize(d2)

        val rdd = rdd1.cartesian(rdd2);
        rdd.collect().foreach(t=>println(t))
    }
}
