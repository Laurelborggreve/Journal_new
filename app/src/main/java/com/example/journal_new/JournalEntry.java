package com.example.journal_new;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    // Declare fields
    private String title, content, mood, timestamp;
    private int id;

    // Method to create a journal entry with information
    public JournalEntry(int journalEntryId, String journalEntryTitle, String journalEntryContent, String journalEntryMood, String journalEntryTimestamp) {
        this.id = journalEntryId;
        this.title = journalEntryTitle;
        this.content = journalEntryContent;
        this.mood = journalEntryMood;
        this.timestamp = journalEntryTimestamp;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


