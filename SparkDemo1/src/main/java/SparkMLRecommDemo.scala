/**
  * Created by Administrator on 2017/4/9.
  */

import org.apache.spark.{SparkConf, SparkContext}
// $example on$
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.mllib.recommendation.Rating
object SparkMLRecommDemo {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("Recommend").setMaster("local[4]")
        val sc = new SparkContext(conf)
        // Load and parse the data

        val data = sc.textFile("file:///d:/scala/ml/recomm/data2.txt")
        //变换数据成为Rating。
        val ratings = data.map(_.split(',') match { case Array(user, item, rate) =>
            Rating(user.toInt, item.toInt, rate.toDouble)
        })

        // Build the recommendation model using ALS
        val rank = 10
        val numIterations = 10
        //交替最小二乘法算法构建推荐模型
        val model = ALS.train(ratings, rank, numIterations, 0.01)

        // 取出评分数据的(User,product)   将用户和商品联系在一起
        val usersProducts = ratings.map { case Rating(user, product, rate) =>
            (user, product)
        }

//        通过model对(user,product)进行预测,((user, product),rate)
        val ug2 = sc.makeRDD(Array((2,3),(2,4)))

//        开始预测
        val predictions =
            model.predict(usersProducts).map { case Rating(user, product, rate) =>
                ((user, product), rate)
         }
//        预测每个用户对每个商品打分多少，之后就可以快速推荐商品
        predictions.collect().foreach(println)


        //向用户推荐n款商品
        //val res = model.recommendProducts(5,8);
        //将指定的商品推荐给n个用户
        //val res = model.recommendUsers(3,5)
        //向所有用户推荐3种商品
        val res = model.recommendProductsForUsers(3)
        res.foreach(e=>{
            println(e._1 + " ======= ")
            e._2.foreach(println)
        })

//        对训练数据进行map ，((user, product),rate)
        val ratesAndPreds = ratings.map { case Rating(user, product, rate) =>
            ((user, product), rate)
        }.join(predictions)
        ratesAndPreds.collect().foreach(println)
//
//        错误均值计算  r1是用户标签值 r2是预测值
//        val MSE = ratesAndPreds.map { case ((user, product), (r1, r2)) =>
//            val err = (r1 - r2)
//            err * err
//        }.mean()
//        println("Mean Squared Error = " + MSE)

//        // Save and load model
//        model.save(sc, "target/tmp/myCollaborativeFilter")
//        val sameModel = MatrixFactorizationModel.load(sc, "target/tmp/myCollaborativeFilter")
        // $example off$
    }
}
