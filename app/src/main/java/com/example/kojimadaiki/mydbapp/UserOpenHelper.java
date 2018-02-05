package com.example.kojimadaiki.mydbapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.FitWindowsViewGroup;

/**
 * Created by kojimadaiki on 2018/01/06.
 */

public class UserOpenHelper extends SQLiteOpenHelper{

    public static  final String DB_NAME = "myapp.db";
    public static  final int DB_VERSION = 1;
    public static final String CREATE_TABLE = "create table " +
            UserContract.Users.TABLE_NAME + "(" +
            UserContract.Users._ID + " integer primary key autoincrement," +
            UserContract.Users.COL_NAME + " text," +
            UserContract.Users.COL_SCORE + " integer)";
    public static final String INIT_TABLE = "insert into users (name,score) values " +
            "('taguchi',42), " +
            "('fkoji',82), " +
            "('dotinstall',62)";
    public static final String DROP_TABLE = "drop table if exists users";


    public UserOpenHelper(Context c){
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(CREATE_TABLE);
        //init table
        db.execSQL(INIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table
        db.execSQL(DROP_TABLE);
        //oncreate
        onCreate(db);
    }
}
