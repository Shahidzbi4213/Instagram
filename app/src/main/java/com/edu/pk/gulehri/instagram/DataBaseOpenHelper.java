package com.edu.pk.gulehri.instagram;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A SQLite helper enables you to create and manage databases.
 * You create one by extending the SQLiteOpenHelper class.*/

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Instagram";
    private static final int DB_VERSION = 1;
    private static final String TAG = "MyTag";

    public DataBaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /** The SQLiteDatabase class gives you access to the database*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
        Log.d(TAG, "onCreate: DataBase Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DELETE_TABLE);
        onCreate(db);

        Log.d(TAG, "onUpgrade: DataBase Upgraded.....");
    }
}
