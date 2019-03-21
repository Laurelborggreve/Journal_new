package com.example.journal_new;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    // Declare fields
 //   private int id;
    private String title, content, mood, timestamp;

    // Method to create journalentry with information
    public JournalEntry(String title, String content, String mood) {
   //     this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
    //    this.timestamp = timestamp;
    }

  //  public int getId() {
//        return id;
//    }

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

    public void setMood(String mood) {
        this.mood = mood;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


