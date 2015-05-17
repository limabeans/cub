package com.angelllim.cube.cub;

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
            "CREATE_TABLE " + SOLVE_TABLE + " (" +
                    SOLVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SOLVE_SCRAMBLE + " TEXT NOT NULL," +
                    SOLVE_TIME + " LONG NOT NULL);";

    public static final String DROP_SOLVE_TABLE =
            "DROP TABLE IF EXISTS " + SOLVE_TABLE;
}
