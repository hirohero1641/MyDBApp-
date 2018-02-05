package com.example.kojimadaiki.mydbapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //open db
        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();

        /***select insert update  delete***/
        //Add Users
        /*ContentValues newUser = new ContentValues();
        newUser.put(UserContract.Users.COL_NAME,"tanaka");
        newUser.put(UserContract.Users.COL_SCORE,"44");
        long newid = db.insert(UserContract.Users.TABLE_NAME
        ,null
        ,newUser);

        //Update Score
        ContentValues newScore = new ContentValues();
        newScore.put(UserContract.Users.COL_SCORE,100);
        int updateLong = db.update(UserContract.Users.TABLE_NAME,
                newScore,
                UserContract.Users.COL_NAME + " =?",
                new String[]{"fkoji"});

        //Delete Score
        int deleteCount = db.delete(UserContract.Users.TABLE_NAME,
                UserContract.Users.COL_NAME + " =?",
                new String[]{"dotinstall"});*/

        //transaction
        try{
            db.beginTransaction();
            db.execSQL("update users "+
            "set score = score +10 "+
            "where name = 'taguchi'");

            db.execSQL("update users "+
                    "set score = score -10 "+
                    "where name = 'fkoji'");

            db.setTransactionSuccessful();
        } catch (SQLException e){
            e.getStackTrace();
        } finally {
            db.endTransaction();
        }


        //処理
        Cursor c = null;
        c = db.query(
                UserContract.Users.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Log.v("DB TEST", "count: " + c.getCount());
        while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex(UserContract.Users._ID));
            String name = c.getString(c.getColumnIndex(UserContract.Users.COL_NAME));
            int score = c.getInt(c.getColumnIndex(UserContract.Users.COL_SCORE));
            Log.v("DB TEST", "id: " + id + ""+ "name: " + "" +  name + "score: " + "" + score);
        }

        c.close();

        //DB close
        db.close();
    }
}
