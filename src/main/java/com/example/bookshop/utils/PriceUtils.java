package com.example.bookshop.utils;

import java.math.BigDecimal;

/**
 * @className: PriceUtils
 * @author: ZhaiJinPei
 * @discription:  对购物车和订单金额进行计算
 * @version: 1.0
 */
public class PriceUtils {
    public static float add(float a, float b) {
        BigDecimal bigA = new BigDecimal(Float.toString(a));
        BigDecimal bigB = new BigDecimal(Float.toString(b));
        return bigA.add(bigB).floatValue();
    }

    public static double add(double a, double b) {
        BigDecimal bigA = new BigDecimal(Double.toString(a));
        BigDecimal bigB = new BigDecimal(Double.toString(b));
        return bigA.add(bigB).doubleValue();
    }

    public static float subtract(float a, float b) {
        BigDecimal bigA = new BigDecimal(Float.toString(a));
        BigDecimal bigB = new BigDecimal(Float.toString(b));
        return bigA.subtract(bigB).floatValue();
    }

    public static double subtract(double a, double b) {
        BigDecimal bigA = new BigDecimal(Double.toString(a));
        BigDecimal bigB = new BigDecimal(Double.toString(b));
        return bigA.subtract(bigB).doubleValue();
    }
}
