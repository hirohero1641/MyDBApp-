package com.example.kojimadaiki.mydbapp;

import android.provider.BaseColumns;

/**
 * Created by kojimadaiki on 2018/01/06.
 */

public final class UserContract {

    public UserContract(){}

    public static abstract class Users implements BaseColumns{

        public static final String TABLE_NAME = "users";
        public static final String COL_NAME = "name";
        public static final String COL_SCORE = "score";

    }

}
