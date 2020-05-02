package com.edu.pk.gulehri.instagram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SourceClass {

    private static final String TAG = "MyTag";
    private Context mContext;
    private DataBaseOpenHelper helper;
    private SQLiteDatabase db;


    SourceClass(Context mContext) {
        this.mContext = mContext;
        helper = new DataBaseOpenHelper(mContext);
        db = helper.getWritableDatabase();
        db = helper.getReadableDatabase();

    }


    /**
     * This method to Add User to the DataBase
     *
     * @param Email
     * @param FName
     * @param UName
     * @param Password
     */

    public void insertData(String Email, String FName, String UName, String Password) {

        /*ContentValues is a name value pair, used to insert or update values into database tables.
          ContentValues object will be passed to SQLiteDataBase objects insert() and update() functions.*/

        ContentValues values = new ContentValues();
        values.put(Constants.EMAIL, Email);
        values.put(Constants.FULL_NAME, FName);
        values.put(Constants.USER_NAME, UName);
        values.put(Constants.PASSWORD, Password);

        Long id = db.insert(Constants.DB_TABLE, null, values);
        Log.d(TAG, "insertData: Row Inserted to DataBase " + id);

    }


    /**
     * This method to check user exist or not
     *
     * @param username
     * @param password
     * @return true/false
     */

    public Boolean validateUser(String username, String password) {

        String selection = Constants.USER_NAME + " = ?" + " AND " + Constants.PASSWORD + " = ?";
        String[] Argument = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT UserName AND Password FROM instagram WHERE user_name = 'shahidzbi' AND user_password = 'Shahid@4213';
         */

        /*A Cursor lets you read from and write to the database.*/
        Cursor cursor = db.query(Constants.DB_TABLE, Constants.All_COLUMNS, selection, Argument, null, null, null);

        int count = cursor.getCount();
        cursor.close();

        /*Here We Check if any record found agaist this query so it will return true else false*/
        if (count > 0) {
            return true;
        }
        return false;

    }

    /**
     * This Method Delete the Specific User from the database
     */
    public void DeleteRow() {
        String Where = Constants.USER_NAME + "=? ";
        String[] WhereArgs = {"Iqbalzbi"};
        final int delete = db.delete(Constants.DB_TABLE, Where, WhereArgs);
        Log.d(TAG, "DeleteRow: " + delete);
    }


    /**
     * This Method Update a row record in the database
     */
    public void UpdateRow(String Name) {
        ContentValues values = new ContentValues(1);
        values.put(Constants.FULL_NAME, Name);

        String Where = Constants.FULL_NAME + " = ?";
        String[] WhereArgs = {"Shahid Iqbal"};


        int update = db.update(Constants.DB_TABLE, values, Where, WhereArgs);
        Log.d(TAG, "UpdateRow: " + update);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }
}
