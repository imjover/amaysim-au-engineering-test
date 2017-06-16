package com.magdaleno.amaysim.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created with Android Studio
 *
 * @author: John Oliver "JoVer" Magdaleno
 * @date: 10/13/2016.
 */

public class Monetary {

    static final private DecimalFormat MONEY_FORMAT = new DecimalFormat("#,##0.00");

    public static String addDecimalToStr(int amount) {
        return String.valueOf(amount/100);
    }

    public static BigDecimal addDecimalToBigDecimal(int amount) {
        return new BigDecimal(amount).divide(new BigDecimal("100"));
    }

    public static BigDecimal removeDecimal(BigDecimal amount) {
        return amount.multiply(new BigDecimal(100));
    }

    public static String valueOf(BigDecimal amount) {
        try {
            return MONEY_FORMAT.format(amount);
        }
        catch (Exception e) {
            return null;
        }
    }
}
