package com.sumixer.ys.api.utils;

/**
 * @author : coderWu
 * @date : Created on 20:30 2018/6/15
 */
public class NumberUtils {

    public static double formatDouble1(double number) {
        return (double) Math.round(number * 100) / 100;
    }

}
