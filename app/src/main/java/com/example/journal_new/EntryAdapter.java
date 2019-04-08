package com.example.journal_new;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.support.v4.widget.ResourceCursorAdapter;
// import android.widget.ResourceCursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;


public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Set date and time
        TextView entryDate = view.findViewById(R.id.entry_date);
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        entryDate.setText(date);

        // Set title
        TextView entryTitle = view.findViewById(R.id.entry_title);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        entryTitle.setText(title);

        // EXTRA FOR IMPROVING PROJECT If a jorunal entry is liked, set a star as image
        String favoriteClicked = (String) view.getTag();
        Log.d("favoriteClicked", "favorteClicked");
        if ("true".equals(favoriteClicked)) {
            ImageView imageView = view.findViewById(R.id.favorite_clicked_image);
            int star = R.drawable.star;
            imageView.setImageResource(star);
        }

        // Set mood
        TextView entryMood = view.findViewById(R.id.entry_mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        entryMood.setText(mood);

        // Set emojis to illustrate mood
        ImageView entryMoodImage = view.findViewById(R.id.entry_mood_image);
        int viewId = R.drawable.expressionless;
        switch(mood) {
            case "emotionless":
                viewId = R.drawable.expressionless;
                break;
            case "smiling":
                viewId = R.drawable.smilingeyes;
                break;
            case "joyful":
                viewId = R.drawable.tearsofjoy;
                break;
            case "in love":
                viewId = R.drawable.inlove;
                break;
            case "pensive":
                viewId = R.drawable.pensive;
                break;
            case "angry":
                viewId = R.drawable.angry;
                break;
            case "sick":
                viewId = R.drawable.vomit;
                break;
        }
        entryMoodImage.setImageResource(viewId);
    }
}
