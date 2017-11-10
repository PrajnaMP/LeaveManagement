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
public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler mDBHandler;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "leave_db";
    private static final String TABLE_NAME = "leave";
    private static final String KEY_NAME = "Name";
    private static final String KEY_MOBILE_NO = "Mobile";
    private static final String KEY_LEAVE_TYPE = "Leave_type";
    private static final String KEY_SICK_LEAVE = "Sick_leave";
    private static final String KEY_CASUAL_LEAVE = "Casual_leave";
    private static final String KEY_EARN_LEAVE = "Earn_leave";
    private static final String KEY_FROM_DATE = "From_date";
    private static final String KEY_TO_DATE = "To_date";
    private static final String KEY_DAYS = "Days";
    private static final String KEY_APPLY_TO = "Apply_to";
    private static final String KEY_REASON = "Reason";

    private DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHandler getDBHandlerInstance(Context context) {
        if (mDBHandler == null) mDBHandler = new DBHandler(context);
        return mDBHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( Name TEXT PRIMARY KEY, Mobile TEXT,Leave_type TEXT," +
                "Sick_leave TEXT,Casual_leave TEXT,Earn_leave TEXT,LOP TEXT,From_date TEXT,To_date TEXT,Days TEXT," +
                "Apply_to TEXT,Reason TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public int updateApplyLeaves(LeaveClass leave, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEAVE_TYPE, leave.getLeaveType());
            values.put(KEY_FROM_DATE, leave.getFromDate());
            values.put(KEY_TO_DATE, leave.getToDate());
            values.put(KEY_DAYS, leave.getDays());
            values.put(KEY_REASON, leave.getReason());
            values.put(KEY_APPLY_TO, leave.getApplyTo());
            values.put(KEY_MOBILE_NO, leave.getMobile());
            return db.update(TABLE_NAME, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(username)});
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
        return 0;
    }

    public long insertAllDataIntoLeaveList(LeaveClass leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long rowInserted = 0;

        try {
            values.put(KEY_NAME, leave.getName());
            values.put(KEY_SICK_LEAVE, leave.getSickLeave());
            values.put(KEY_CASUAL_LEAVE, leave.getCasualLeave());
            values.put(KEY_EARN_LEAVE, leave.getEarnLeave());
            values.put(KEY_APPLY_TO, leave.getApplyTo());
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


    public ArrayList<LeaveClass> getAllStoredEmployeeRecord() {
        ArrayList<LeaveClass> EmployeeList = new ArrayList<LeaveClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getWritableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    LeaveClass leave = new LeaveClass();
                    leave.setName(cursor.getString(0));
                    leave.setSickLeave(cursor.getString(3));
                    leave.setCasualLeave(cursor.getString(4));
                    leave.setEarnLeave(cursor.getString(5));
                    EmployeeList.add(leave);
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
            if (EmployeeList != null && EmployeeList.size() > 0) {
                return EmployeeList;
            } else {
                return null;
            }
        }
    }

    public LeaveClass getLeaves(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        LeaveClass leave = null;
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_NAME, KEY_SICK_LEAVE, KEY_CASUAL_LEAVE, KEY_EARN_LEAVE}, KEY_NAME + "=?", new String[]{String.valueOf(name)}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            leave = new LeaveClass();
            leave.setName(cursor.getString(0));
            leave.setSickLeave(cursor.getString(1));
            leave.setCasualLeave(cursor.getString(2));
            leave.setEarnLeave(cursor.getString(3));
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return leave;
    }

    public int updateEmployeeLeaves(LeaveClass leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, leave.getName());
            values.put(KEY_SICK_LEAVE, leave.getSickLeave());
            values.put(KEY_CASUAL_LEAVE, leave.getCasualLeave());
            values.put(KEY_EARN_LEAVE, leave.getEarnLeave());
            // updating row
            return db.update(TABLE_NAME, values, KEY_NAME + " = ?",
                    new String[]{String.valueOf(leave.getName())});
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
        return 0;
    }

    public void deleteEmployee(LeaveClass leave) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int deletedCount = db.delete(TABLE_NAME, KEY_NAME + " = ?",
                    new String[]{String.valueOf(leave.getName())});
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
    }

    public LeaveClass getLastLeave(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        LeaveClass leave = null;
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_LEAVE_TYPE,
                            KEY_FROM_DATE, KEY_TO_DATE, KEY_DAYS, KEY_REASON}, KEY_NAME + "=?", new String[]{String.valueOf(name)},
                    null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            leave = new LeaveClass();
            leave.setLeaveType(cursor.getString(0));
            leave.setFromDate(cursor.getString(1));
            leave.setToDate(cursor.getString(2));
            leave.setDays(cursor.getString(3));
            leave.setReason(cursor.getString(4));
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return leave;
    }
}
