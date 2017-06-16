package com.magdaleno.amaysim.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created with Android Studio
 *
 * @author: John Oliver "JoVer" Magdaleno
 * @date: 10/13/2016.
 */

public class Data {

    static final private DecimalFormat GB_FORMAT = new DecimalFormat("#0.0");

    public static String mbToGB_toStr(int amount) {
        return String.valueOf(amount/1000);
    }

    public static BigDecimal mbToGB_toBigDecimal(int amount) {
        return new BigDecimal(amount).divide(new BigDecimal("1000"));
    }

    public static String valueOf(BigDecimal amount) {
        try {
            return GB_FORMAT.format(amount);
        }
        catch (Exception e) {
            return null;
        }
    }
}
