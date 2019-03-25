package com.example.journal_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "TABLE_NAME";
    public static final int DB_VERSION = 12;
    public static final String id = "_id";
    public static final String title = "title";
    public static final String content = "content";
    public static final String mood = "mood";
    public static final String timestamp = "timestamp";
    private static EntryDatabase instance;


    private EntryDatabase(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }
    // Method to create table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE IF NOT EXISTS" + TABLE_NAME + " (" + id + "INTEGER PRIMARY KEY AUTOINCREMENT," + title + " TEXT NOT NULL, " + content + " TEXT NOT NULL, " + mood + " TEXT NOT NULL, " + timestamp + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP " + ");";
        database.execSQL(createTable);
    }

    // Method to upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // Drop version if table exists
        database.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);

        // Create new table
        onCreate(database);
    }

    // Method to check whether an instance of EntryDatabase currently exists. If yes, return it.
    // If it does not exist yet, create it
    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new EntryDatabase(context);
            return instance;
        }
    }

    // Method to insert and delete journalentries in database
    public Cursor selectAll() {
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM TABLE_NAME", null);
        return cursor;
    }

    public void insert(JournalEntry journalentry) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentvalue = new ContentValues();
        contentvalue.put("title", journalentry.getTitle());
        contentvalue.put("content", journalentry.getContent());
        contentvalue.put("mood", journalentry.getMood());
        database.insert("TABLE_NAME", null, contentvalue);
    }

    public void delete(long id) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + "WHERE" + id + "=" + id);
    }

}
