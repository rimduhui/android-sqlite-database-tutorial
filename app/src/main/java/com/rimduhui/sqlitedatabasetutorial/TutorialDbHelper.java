package com.rimduhui.sqlitedatabasetutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TutorialDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Tutorial.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TutorialContract.TutorialEntry.TABLE_NAME + " (" +
                    TutorialContract.TutorialEntry._ID + " INTEGER PRIMARY KEY," +
                    TutorialContract.TutorialEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    TutorialContract.TutorialEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TutorialContract.TutorialEntry.TABLE_NAME;

    public TutorialDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
