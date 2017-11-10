package com.example.prajna.leave;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by prajna on 28/9/16.
 */
public class DBHandlerForHolidays extends SQLiteOpenHelper {

    private static DBHandlerForHolidays mDBHandler;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "holiday_db";
    private static final String TABLE_NAME = "holiday";
    private static final String KEY_DATE = "Date";
    private static final String KEY_HOLIDAY = "Day";

    private DBHandlerForHolidays(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHandlerForHolidays getDBHandlerInstance(Context context) {
        if (mDBHandler == null) mDBHandler = new DBHandlerForHolidays(context);
        return mDBHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( Date TEXT , Day TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<LeaveClass> getAllHolidays() {
        ArrayList<LeaveClass> LeaveList = new ArrayList<LeaveClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getWritableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    LeaveClass leave = new LeaveClass();
                    leave.setDate(cursor.getString(0));
                    leave.setHoliday(cursor.getString(1));

                    LeaveList.add(leave);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqliteException) {
            sqliteException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
            if (LeaveList != null && LeaveList.size() > 0) {
                return LeaveList;
            } else {
                return null;
            }
        }
    }

    public long insertAllHoliday(LeaveClass leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long rowInserted = 0;

        try {
            values.put(KEY_DATE, leave.getDate());
            values.put(KEY_HOLIDAY, leave.getHoliday());
            rowInserted = db.insert(TABLE_NAME, null, values);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return rowInserted;
    }

    public void deleteHoliday(LeaveClass leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int deletedCount = db.delete(TABLE_NAME, KEY_HOLIDAY + " = ?",
                    new String[]{String.valueOf(leave.getHoliday())});
            db.close();
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
    }

}