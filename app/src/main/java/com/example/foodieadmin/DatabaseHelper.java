package com.example.foodieadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Context
    private Context context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "FOODIE_ADMIN";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Category.CREATE_TABLE);
        Toast.makeText(context, "Database created",Toast.LENGTH_SHORT)
                .show();
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(Category.DROP_TABLE);

        Toast.makeText(context, "Database upgrading...",Toast.LENGTH_SHORT)
                .show();

        // Create tables again
        onCreate(db);
    }

    public long insertCategory(String name) {
        Toast.makeText(context, "calling insertCategory()",Toast.LENGTH_SHORT)
                .show();
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Category.COLUMN_NAME, name);

        // insert row
        long id = db.insert(Category.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
}
