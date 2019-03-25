package com.example.journal_new;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    // Declare fields
    private String title, content, mood, timestamp;
    private int id;

    // Method to create a journal entry with information
    public JournalEntry(int JE_id, String JE_title, String JE_content, String JE_mood, String JE_timestamp) {
        this.id = JE_id;
        this.title = JE_title;
        this.content = JE_content;
        this.mood = JE_mood;
        this.timestamp = JE_timestamp;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


