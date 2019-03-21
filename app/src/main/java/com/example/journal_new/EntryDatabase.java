package com.example.journal_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "TABLE_NAME";
    public static final int DB_VERSION = 9;
//    public static final String id = "_id";
//    public static final String title = "title";
//    public static final String content = "content";
//    public static final String mood = "mood";
//    public static final String timestamp = "timestamp";
    private static EntryDatabase instance;


    private EntryDatabase(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }

    // Method to create table
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String table = "CREATE TABLE IF NOT EXISTS" + TABLE_NAME + " (" + id + "INTEGER PRIMARY KEY AUTOINCREMENT," + title + " TEXT NOT NULL, " + content + " TEXT NOT NULL, " + mood + " TEXT NOT NULL, " + timestamp + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP " + ");";
//        db.execSQL(table);
//        db.execSQL("INSERT INTO TABLE_NAME (title, content, mood) VALUES (\"test\", \"best\", \"great\")");
        String createTable = "CREATE TABLE TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT, timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createTable);
        db.execSQL("INSERT INTO TABLE_NAME (title, content, mood) VALUES (\"test\", \"best\", \"great\")");
    }

    // Method to upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop version if table exists
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
//
//        // Create new table
//        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        onCreate(db);
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


    // Method ...
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
        long numRows = DatabaseUtils.queryNumEntries(database, "TABLE_NAME");
        System.out.println("aantal " + numRows);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM TABLE_NAME WHERE id = _id");
    //    db.execSQL("DELETE FROM " + TABLE_NAME + "WHERE" + id + "=" + id);
        db.delete(TABLE_NAME, "_id=" + id, null);
    }

}
