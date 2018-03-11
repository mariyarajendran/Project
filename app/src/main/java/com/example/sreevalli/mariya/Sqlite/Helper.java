package com.example.sreevalli.mariya.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sreevalli on 3/10/2018.
 */

public class Helper extends SQLiteOpenHelper {

    public Helper(Context context) {
        super(context, "DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table datas " +
                "(id integer primary key AUTOINCREMENT, name text,area text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS datas");
        onCreate(db);

    }

    public void insert(String name,String area){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("area", area);
        db.insert("datas", null, contentValues);


    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "datas");
        return numRows;
    }


    public Cursor display(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor=db.rawQuery(("SELECT * FROM datas"), null);
        return cursor;
    }
}
