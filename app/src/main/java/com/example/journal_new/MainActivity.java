package com.example.journal_new;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all records from the database
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(getApplicationContext(), db.selectAll());
        ListView listview = findViewById(R.id.adapterlistview);
        listview.setOnItemClickListener(new ItemClickListener());
        listview.setOnItemLongClickListener(new ItemLongClickListener());
        Parcelable state = listview.onSaveInstanceState();
        listview.setAdapter(adapter);
        listview.onRestoreInstanceState(state);
      //  setAdapter();
    }

    public void FABClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // Method to instantiate the adapter
//    public void setAdapter() {
//        db = EntryDatabase.getInstance(getApplicationContext());
//        adapter = new EntryAdapter(getApplicationContext(), db.selectAll());
//        ListView listview = findViewById(R.id.adapterlistview);
//        listview.setOnItemClickListener(new ItemClickListener());
//        listview.setOnItemLongClickListener(new ItemLongClickListener());
//        Parcelable state = listview.onSaveInstanceState();
//        listview.setAdapter(adapter);
//        listview.onRestoreInstanceState(state);
//
//    }

    private class ItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Go to Detail activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // retrieve item that was clicked
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            JournalEntry journalentry = new JournalEntry(
                    //cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("mood")));
                //    cursor.getString(cursor.getColumnIndex("timestamp")));
            journalentry.setTimestamp(cursor.getString(cursor.getColumnIndex("timestamp")));
            intent.putExtra("journalentry", journalentry);
            startActivity(intent);
        }
    }

    private class ItemLongClickListener implements AdapterView.OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
     //       db = EntryDatabase.getInstance(getApplicationContext());

            // retrieve item that was clicked
            Cursor clickedjournalentry = (Cursor) parent.getItemAtPosition(position);

            // delete selected item from database
            db.delete(clickedjournalentry.getInt(clickedjournalentry.getColumnIndex("_id")));

            // Set adapter and update data
          //  setAdapter();
            updateData();
            return true;
        }
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    @Override
    public void onResume() {
        System.out.println("testjes");
        super.onResume();
        adapter.notifyDataSetChanged();
    }


}
