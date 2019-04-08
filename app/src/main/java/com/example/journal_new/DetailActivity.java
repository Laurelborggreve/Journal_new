package com.example.journal_new;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    public String titleEntry;
    public String favoriteClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Extract intent
        Intent intent = getIntent();
        JournalEntry retrievedJournalEntry = (JournalEntry) intent.getSerializableExtra("journalentry");

        // Show contents of retrieved journal entries in the appropriate views
        TextView detailTimestamp = findViewById(R.id.detail_timestamp);
        TextView detailMood = findViewById(R.id.detail_mood);
        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailContent = findViewById(R.id.detail_content);

        detailTimestamp.setText("The date of this journal is: " + retrievedJournalEntry.getTimestamp());
        detailMood.setText("My mood was: " + retrievedJournalEntry.getMood());
        detailTitle.setText(retrievedJournalEntry.getTitle());
        detailContent.setText(retrievedJournalEntry.getContent());

        ImageButton favoriteButton = findViewById(R.id.image_button);
        favoriteButton.setOnClickListener(new LikeButtonListener());

        titleEntry = retrievedJournalEntry.getTitle();
    }

    // Method to save a liked journal entry
    private class LikeButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), titleEntry + " is now a favorite!", Toast.LENGTH_SHORT).show();
            ImageButton favoriteButton = findViewById(R.id.image_button);
            favoriteButton.setTag("true");

            // Start new activity
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            intent.putExtra("favoriteClicked", favoriteClicked);
            startActivity(intent);
        }
    }
}
