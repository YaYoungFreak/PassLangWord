package com.teamlearn.passlangword;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainDBCode {
    private Context context;
    private DatabaseHelper DatabaseHelper;
    private SQLiteDatabase db;
    public MainDBCode(Context context){
        this.context = context;
        DatabaseHelper = new DatabaseHelper(context);
    }
    public void OpenReadDB(){
        db = DatabaseHelper.getReadableDatabase();
    }
    public void insertToDB(String word, String translate){
        ContentValues cv = new ContentValues();
        cv.put(DBInfo.COLUMN_WORD, word);
        cv.put(DBInfo.COLUMN_TRANSLATE, translate);
        db.insert(DBInfo.TABLE_NAME, null, cv);
    }
    public List<String> readingDB(){
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(DBInfo.TABLE_NAME, null,null,
                null,null,null,null);

        while (cursor.moveToNext()){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DBInfo.COLUMN_WORD));
            tempList.add(title);
        }
        cursor.close();
        return tempList;
    }

    public String randomnum;
    public String rand_ask_word;
    public String successful_answer;
    public long rowCount;
    private String ChangedCategory;

    @SuppressLint("Range")
    public void ChangeRandomWord() {
        SharedPreferences pref;
        pref = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        ChangedCategory = (pref.getString("Category", DBInfo.TABLE_NAME));

        rowCount  = DatabaseUtils.queryNumEntries(db, ChangedCategory); //Находим количество строк в таблице(длину)

        Cursor cursor = db.query(ChangedCategory, null,null,
            null,null,null,null);
        Random random = new Random();
        int rand = random.nextInt((int) rowCount);
        cursor.moveToPosition(rand);

        rand_ask_word = (cursor.getString(cursor.getColumnIndex(DBInfo.COLUMN_WORD)));
        successful_answer = cursor.getString(cursor.getColumnIndex(DBInfo.COLUMN_TRANSLATE));
        randomnum = String.valueOf(rand + 1);
    }
    public void closeDB(){
        DatabaseHelper.close();
    }

}
