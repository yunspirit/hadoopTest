package com.it18zhang.hivedemo.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 自定义hive函数
 */
//name  函数名称
//value：帮助信息
//extended：实例
//    当执行show function myadd是会显示如下信息
@Description(name = "myadd",
        value = "add(int a , int b) ==> return a + b ",
        extended = "Example:\n"
                + " add(1,1) ==> 2 \n"
                + " add(1,2,3) ==> 6;")
public class AddUDF extends UDF {


    //      com.it18zhang.hivedemo.udf.AddUDF
    public int evaluate(int a ,int b) {
        return a + b ;
    }

    public int evaluate(int a ,int b , int c) {
        return a + b + c;
    }
}