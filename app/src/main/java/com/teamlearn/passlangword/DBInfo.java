package com.teamlearn.passlangword;

import android.content.Context;
import android.os.Build;

public class DBInfo {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "words.db";
    public static final String TABLE_NAME = "wortest";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_TRANSLATE = "translate";
    public static final String DB_DATA_PATH = "/data/data/com.teamlearn.passlangword/databases/words.db";
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_WORD + " TEXT," + COLUMN_TRANSLATE + " TEXT" + ")";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
