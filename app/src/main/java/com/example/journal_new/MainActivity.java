package com.example.journal_new;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Objects;

import static com.example.journal_new.R.drawable.star_empty;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     // Get all records from the database
        setAdapter();
    }

    public void FABClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // Method to instantiate the adapter
    public void setAdapter() {
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(getApplicationContext(), db.selectAll());

        ListView listView = findViewById(R.id.adapterlistview);
        listView.setOnItemClickListener(new ItemClickListener());
        listView.setOnItemLongClickListener(new ItemLongClickListener());
        listView.setAdapter(adapter);
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Go to Detail activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // Retrieve item that was clicked
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            JournalEntry journalEntry = new JournalEntry(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("mood")),
                    cursor.getString(cursor.getColumnIndex("timestamp")));
            journalEntry.setTimestamp(cursor.getString(cursor.getColumnIndex("timestamp")));
            intent.putExtra("journalentry", journalEntry);
            startActivity(intent);
        }
    }

    private class ItemLongClickListener implements AdapterView.OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            db = EntryDatabase.getInstance(getApplicationContext());

            // Retrieve item that was clicked
            Cursor clickedJournalEntry = (Cursor) parent.getItemAtPosition(position);

            // Delete selected item from database
            db.delete(clickedJournalEntry.getInt(clickedJournalEntry.getColumnIndex("_id")));

            // Set adapter and update data
            updateData();
            return true;
        }
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
