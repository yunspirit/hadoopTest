package com.it18zhang.portrait;

import java.util.Base64;

/**
 * Created by Administrator on 2017/5/9.
 */
public class Base64Demo {
    public static void main(String[] args) {
        byte[] bytes  = Base64.getDecoder().decode("NXxY3tn5XsuFcyzEw8qP8g==".getBytes());
        System.out.println(new String(bytes));
    }
}
