package com.example.journal_new;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.View;
import android.support.v4.widget.ResourceCursorAdapter;
// import android.widget.ResourceCursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Set date and time
        TextView entry_date = view.findViewById(R.id.entry_date);
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        entry_date.setText(date);

        // Set title
        TextView entry_title = view.findViewById(R.id.entry_title);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        entry_title.setText(title);

        // Set mood
        TextView entry_mood = view.findViewById(R.id.entry_mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        entry_mood.setText(mood);

        // Set emojis to illustrate mood
        ImageView entry_mood_image = view.findViewById(R.id.entry_mood_image);
        int view_id = R.drawable.expressionless;
        switch(mood) {
            case "emotionless":
                view_id = R.drawable.expressionless;
                break;
            case "smiling":
                view_id = R.drawable.smilingeyes;
                break;
            case "joyful":
                view_id = R.drawable.tearsofjoy;
                break;
            case "in love":
                view_id = R.drawable.inlove;
                break;
            case "pensive":
                view_id = R.drawable.pensive;
                break;
            case "angry":
                view_id = R.drawable.angry;
                break;
            case "sick":
                view_id = R.drawable.vomit;
                break;
        }
        entry_mood_image.setImageResource(view_id);
    }
}
