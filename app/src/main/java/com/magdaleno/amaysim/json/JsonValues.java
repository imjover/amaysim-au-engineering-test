package com.magdaleno.amaysim.json;

import com.magdaleno.amaysim.R;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class JsonValues {
    public static abstract class Types {
        public static final String ACCOUNTS = "accounts";
        public static final String SERVICES = "services";
        public static final String SUBSCRIPTIONS = "subscriptions";
        public static final String PRODUCTS = "products";
    }
    public static abstract class BoolTypes {
        public static Integer getImageRes(boolean bool) {
            return bool ? R.drawable.ic_check : R.drawable.ic_close;
        }
    }
}
