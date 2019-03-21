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
        detail_timestamp.setText(retrievedjournalentry.getTimestamp());

        TextView detail_mood = findViewById(R.id.detail_mood);
        detail_mood.setText(retrievedjournalentry.getMood());
        detail_mood.setTypeface(null, Typeface.ITALIC);

        TextView detail_title = findViewById(R.id.detail_title);
        detail_title.setText(retrievedjournalentry.getTitle());

        TextView detail_content = findViewById(R.id.detail_content);
        detail_content.setText(retrievedjournalentry.getContent());
    }
}
