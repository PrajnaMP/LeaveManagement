package com.example.prajna.leave;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by prajna on 19/9/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version" + _oldVersion + " to " + _newVersion + ", which will destroy all old data");

// The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        onCreate(_db);
    }

}