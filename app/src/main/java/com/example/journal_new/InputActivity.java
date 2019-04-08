package com.example.journal_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InputActivity extends AppCompatActivity {
    private String mood = "Emotionless";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void submitEntryClicked(View view){
        addEntry();
    }

    // Method to add a journal entry
    public void addEntry() {
        EditText insertTitle = findViewById(R.id.insert_title);
        String title = insertTitle.getText().toString();

        EditText insertContent = findViewById(R.id.insert_content);
        String content = insertContent.getText().toString();

        JournalEntry journalEntry = new JournalEntry(0, title, content, mood, "");
        EntryDatabase.getInstance(getApplicationContext()).insert(journalEntry);

        // Go back to Main Activity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Set emojis to illustrate the mood
    public void moodClicked(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.emotionless:
                mood = "emotionless";
                break;
            case R.id.smiling:
                mood = "smiling";
                break;
            case R.id.joyful:
                mood = "joyful";
                break;
            case R.id.inlove:
                mood = "in love";
                break;
            case R.id.pensive:
                mood = "pensive";
                break;
            case R.id.angry:
                mood = "angry";
                break;
            case R.id.sick:
                mood = "sick";
                break;
        }
        Toast.makeText(this, "You selected " + mood, Toast.LENGTH_LONG).show();
    }
}
