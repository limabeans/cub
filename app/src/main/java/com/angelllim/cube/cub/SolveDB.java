package com.angelllim.cube.cub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lima on 5/17/15.
 */
public class SolveDB {
    // database constants
    public static final String DB_NAME = "solve.db";
    public static final int DB_VERSION = 1;

    // solve table constants
    public static final String SOLVE_TABLE = "solve";

    public static final String SOLVE_ID = "_id";
    public static final int SOLVE_ID_COL = 0;

    public static final String SOLVE_SCRAMBLE = "scramble";
    public static final int SOLVE_SCRAMBLE_COL = 1;

    public static final String SOLVE_TIME = "time";
    public static final int SOLVE_TIME_COL = 2;

    //CREATE and DROP TABLE statements
    public static final String CREATE_SOLVE_TABLE =
            "CREATE TABLE " + SOLVE_TABLE + " (" +
                    SOLVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SOLVE_SCRAMBLE + " TEXT NOT NULL, " +
                    SOLVE_TIME + " LONG NOT NULL);";

    public static final String DROP_SOLVE_TABLE =
            "DROP TABLE IF EXISTS " + SOLVE_TABLE;



    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public SolveDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWritableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    public long insertSolve(Solve solve) {
        Log.d("test", solve.toString());

        ContentValues cv = new ContentValues();

        cv.put(SOLVE_TIME, solve.getMillisTime());
        cv.put(SOLVE_SCRAMBLE, solve.getScramble());

        Log.d("cv", cv.toString());

        this.openWritableDB();
        long rowId = db.insert(SOLVE_TABLE, null, cv);
        this.closeDB();

        return rowId;
    }


    public ArrayList<Solve> getSolves() {

        this.openReadableDB();
        Cursor cursor = db.query(SOLVE_TABLE, null, null, null, null, null, null);
        ArrayList<Solve> solves = new ArrayList<Solve>();

        while (cursor.moveToNext()) {
            solves.add(getSolveFromCursor(cursor));
        }

        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();
        return solves;
    }

    public Solve getSolve(int id) {
        String where = SOLVE_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();

        Cursor cursor = db.query(SOLVE_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Solve solve = getSolveFromCursor(cursor);
        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();

        return solve;
    }



    private static Solve getSolveFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        } else {
            try {
                Solve solve = new Solve(
                        cursor.getInt(SOLVE_ID_COL),
                        cursor.getString(SOLVE_SCRAMBLE_COL),
                        cursor.getLong(SOLVE_TIME_COL)
                );
                return solve;
            } catch (Exception e) {
                return null;
            }
        }
    }



    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory, int version) {

            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_SOLVE_TABLE);

            //insert sample time
            db.execSQL("INSERT INTO solve VALUES ( 0, 'F D U', 10000 )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("onUpgrade", "from " + oldVersion + " to " + newVersion);
            db.execSQL(SolveDB.DROP_SOLVE_TABLE);
            onCreate(db);
        }
    }

}
