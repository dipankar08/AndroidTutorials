package com.dipankar.androidtutorials;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import java.util.List;

/**
 * Created by ddutta on 9/20/2014.
 * Dipankar: Works
 * 3 things to Do
 * 1) Creting amodel of userData
 * 2) Define an manager class which actylly access it
 * 3) crete an activity to use this,.
 */

public class SqliteDatabase extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_click);

        /******************* Intialize Database *************/
        DBAdapter.init(this);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        DBAdapter.addUserData(new UserData("Shubham", "9999999999"));
        DBAdapter.addUserData(new UserData("Deny", "8888888888"));
        DBAdapter.addUserData(new UserData("Shanker", "22222222222"));
        DBAdapter.addUserData(new UserData("Sam", "11111111111"));
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<UserData> data = DBAdapter.getAllUserData();
        for (UserData dt : data) {
            String log = "Id: "+dt.getID()+" ,Name: " + dt.getName() + " ,Phone: " + dt.getEmail();
            Log.d("Name: ", log);
        }
        //DBAdapter.getAdminNumberTop();
    }
}

/* define Model Here
* UserData[id,name,email]
*
* */

class UserData {

    //private variables
    int _id;
    String _name;
    String _email;

    // Empty constructor
    public UserData(){

    }
    // constructor
    public UserData(int id, String name, String email){
        this._id = id;
        this._name = name;
        this._email = email;
    }

    // constructor
    public UserData(String name, String email){
        this._name = name;
        this._email = email;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting email
    public String getEmail(){
        return this._email;
    }

    // setting email
    public void setEmail(String email){
        this._email = email;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserInfo [name=" + _name + ", email=" + _email + "]";
    }

}

/* Define dataBase Manager */

class DBAdapter {

    /******************** if debug is set true then it will show all Logcat message ************/
    public static final boolean DEBUG = true;
    /******************** Logcat TAG ************/
    public static final String LOG_TAG = "DBAdapter";
    /******************** Table Fields ************/
    public static final String KEY_ID = "_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_EMAIL = "user_email";

    /******************** Database Name ************/
    public static final String DATABASE_NAME = "DB_sqllite";
    /******************** Database Version (Increase one if want to also upgrade your database) ************/
    public static final int DATABASE_VERSION = 1;// started at 1
    /** Table names */
    public static final String USER_TABLE = "tbl_user";
    /******************** Set all table with comma seperated like USER_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES = { USER_TABLE };
    /** Create table syntax */
    private static final String USER_CREATE = "create table tbl_user(_id integer primary key autoincrement, user_name text not null, user_email text not null);";

    /******************** Used to open database in syncronized way ************/
    private static DataBaseHelper DBHelper = null;

    protected DBAdapter() {
    }

    /******************* Initialize database *************/
    public static void init(Context context) {
        if (DBHelper == null) {
            if (DEBUG)
                Log.i("DBAdapter", context.toString());
            DBHelper = new DataBaseHelper(context);
        }
    }

    /********************** Main Database creation INNER class ********************/
    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (DEBUG)
                Log.i(LOG_TAG, "new create");
            try {
                db.execSQL(USER_CREATE); //SQL for Create..
            } catch (Exception exception) {
                if (DEBUG)
                    Log.i(LOG_TAG, "Exception onCreate() exception");
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (DEBUG)
                Log.w(LOG_TAG, "Upgrading database from version" + oldVersion + "to" + newVersion + "...");
            for (String table : ALL_TABLES) {
                db.execSQL("DROP TABLE IF EXISTS " + table);     // SQl for Delete
            }
            onCreate(db);
        }

    } // Inner class closed


    /********************** Open database for insert,update,delete in syncronized manner ********************/
    private static synchronized SQLiteDatabase open() throws SQLException {
        return DBHelper.getWritableDatabase();
    }


    /*########################## General functions ####################*/
    /*********************** Escape string for single quotes (Insert,Update)************/
    private static String sqlEscapeString(String aString) {
        String aReturn = "";
        if (null != aString) {
            //aReturn = aString.replace("'", "''");
            aReturn = DatabaseUtils.sqlEscapeString(aString);
            // Remove the enclosing single quotes ...
            aReturn = aReturn.substring(1, aReturn.length() - 1);
        }
        return aReturn;
    }
    /*********************** UnEscape string for single quotes (show data)************/
    private static String sqlUnEscapeString(String aString) {
        String aReturn = "";
        if (null != aString) {
            aReturn = aString.replace("''", "'");
        }
        return aReturn;
    }
    /********************************************************************/


    /*##################### All Operations (Create, Read, Update, Delete) ######################*/

    public static void addUserData(UserData uData) {
        final SQLiteDatabase db = open();
        String name = sqlEscapeString(uData.getName());
        String email = sqlEscapeString(uData.getEmail());
        ContentValues cVal = new ContentValues();
        cVal.put(KEY_USER_NAME, name);
        cVal.put(KEY_USER_EMAIL, email);
        db.insert(USER_TABLE, null, cVal);
        db.close(); // Closing database connection
    }

    public static UserData getUserData(int id) {
        final SQLiteDatabase db = open();
        Cursor cursor = db.query(USER_TABLE, new String[] { KEY_ID, KEY_USER_NAME, KEY_USER_EMAIL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        UserData data = new UserData(Integer.parseInt(cursor.getString(0)),  cursor.getString(1), cursor.getString(2));
        return data;
    }

    public static List<UserData> getAllUserData() {
        List<UserData> contactList = new ArrayList<UserData>();
        String selectQuery = "SELECT  * FROM " + USER_TABLE;
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                UserData data = new UserData();
                data.setID(Integer.parseInt(cursor.getString(0)));
                data.setName(cursor.getString(1));
                data.setEmail(cursor.getString(2));
                contactList.add(data);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public static int updateUserData(UserData data) {
        final SQLiteDatabase db = open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, data.getName());
        values.put(KEY_USER_EMAIL, data.getEmail());
        return db.update(USER_TABLE, values, KEY_ID + " = ?",  new String[] { String.valueOf(data.getID()) });
    }

    public static void deleteUserData(UserData data) {
        final SQLiteDatabase db = open();
        db.delete(USER_TABLE, KEY_ID + " = ?",  new String[] { String.valueOf(data.getID()) });
        db.close();
    }

    public static int getUserDataCount() {
        String countQuery = "SELECT  * FROM " + USER_TABLE;
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}

/* Define the activity now */