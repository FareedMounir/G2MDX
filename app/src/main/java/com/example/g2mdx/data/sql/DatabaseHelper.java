package com.example.g2mdx.data.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "items.db";
    private String tableName = "items";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " TITLE TEXT, CONTENT TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE  EXISTS " + tableName);
        onCreate(db);
    }

    public void addItem(String title, String content, DatabaseHandler databaseHandler) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", title);
        contentValues.put("CONTENT", content);

        long result = db.insert(tableName, null, contentValues);

        if (result == -1) {
            databaseHandler.showDatabaseMessage("Something went wrong");
        } else {
            databaseHandler.onSuccess();
        }
    }

    public Cursor getItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }

}
