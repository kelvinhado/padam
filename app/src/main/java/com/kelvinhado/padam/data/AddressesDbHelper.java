package com.kelvinhado.padam.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kelvinhado.padam.data.AddressesContract.AddressesEntry;

/**
 * Created by kelvin on 01/09/2017.
 */

public class AddressesDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "addresses.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public AddressesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // String query that will create the table create a table to hold addresses data
        final String SQL_CREATE_ADDRESSES_TABLE = "CREATE TABLE " +
                AddressesEntry.TABLE_NAME + " (" +
                AddressesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AddressesEntry.COLUMN_ADDRESS_NAME + " TEXT NOT NULL," +
                AddressesEntry.COLUMN_ADDRESS_LATITUDE + " REAL NOT NULL," +
                AddressesEntry.COLUMN_ADDRESS_LONGITUDE + " REAL NOT NULL" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_ADDRESSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // To simplify we'll just drop the table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AddressesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}