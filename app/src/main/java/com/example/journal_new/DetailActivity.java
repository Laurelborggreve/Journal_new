package com.example.journal_new;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Extract intent
        Intent intent = getIntent();
        JournalEntry retrievedjournalentry = (JournalEntry) intent.getSerializableExtra("journalentry");

        // Show contents of retrieved journal entries in the appropriate views
        TextView detail_timestamp = findViewById(R.id.detail_timestamp);
        TextView detail_mood = findViewById(R.id.detail_mood);
        TextView detail_title = findViewById(R.id.detail_title);
        TextView detail_content = findViewById(R.id.detail_content);

        detail_timestamp.setText("The date of this journal is: " + retrievedjournalentry.getTimestamp());
        detail_mood.setText("I was: " + retrievedjournalentry.getMood());
        detail_title.setText(retrievedjournalentry.getTitle());
        detail_content.setText(retrievedjournalentry.getContent());
    }
}
