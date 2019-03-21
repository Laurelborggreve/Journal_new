package com.example.journal_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InputActivity extends AppCompatActivity {
    private String mood = "happy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void sumbitentryClicked(View view){
        addEntry();
    }

    // Method to add a journal entry
    public void addEntry() {
        EditText insert_title = findViewById(R.id.insert_title);
        EditText insert_content = findViewById(R.id.insert_content);
       // EditText insert_mood = findViewById(R.id.insert_mood);

        String title = insert_title.getText().toString();
        String content = insert_content.getText().toString();
      //  String mood = insert_mood.getText().toString();

        JournalEntry journalentry = new JournalEntry(title, content, mood);
        EntryDatabase.getInstance(getApplicationContext()).insert(journalentry);

        // Go back to Main Activity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void moodClicked(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.sad:
                mood = "sad";
                break;
            case R.id.depressive:
                mood = "depressive";
                break;
            case R.id.funny:
                mood = "funny";
                break;
            case R.id.mad:
                mood = "mad";
                break;
            case R.id.great:
                mood = "great";
                break;
            case R.id.happy:
                mood = "happy";
                break;
        }
        Toast.makeText(getApplicationContext(), mood + " is selected", Toast.LENGTH_SHORT).show();
    }
}
