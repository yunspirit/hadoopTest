package com.it18zhang.portrait;

/**
 * Created by Administrator on 2017/5/9.
 */
public class Portrait {
    //男权重
    private double male ;
    //女权重
    private double female ;

    public void protraitSex(double male2, double female2, long times) {

        double sum = this.male + this.female + (male2 + female2) * times;

        if (sum != 0) {
            this.male = (this.male + male2 * times) / sum;
            this.female = (this.female + female2 * times) / sum;
        }
    }


    public double getMale() {
        return male;
    }

    public void setMale(double male) {
        this.male = male;
    }

    public double getFemale() {
        return female;
    }

    public void setFemale(double female) {
        this.female = female;
    }
}
