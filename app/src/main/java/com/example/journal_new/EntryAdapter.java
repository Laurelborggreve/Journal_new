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
        TextView entry_date = view.findViewById(R.id.entry_date);
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        entry_date.setText(date);

        TextView entry_title = view.findViewById(R.id.entry_title);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        entry_title.setText(title);

        TextView entry_mood = view.findViewById(R.id.entry_mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        entry_mood.setText(mood);
        entry_mood.setTypeface(null, Typeface.ITALIC);
        int drawableId = R.drawable.funny;
        switch(mood) {
            case "funny":
                drawableId = R.drawable.funny;
                break;
            case "great":
                drawableId = R.drawable.amazing;
                break;
            case "depressive":
                drawableId = R.drawable.depressive;
                break;
            case "happy":
                drawableId = R.drawable.happy;
                break;
            case "mad":
                drawableId = R.drawable.mad;
                break;
            case "sad":
                drawableId = R.drawable.sad;
                break;
        }
        ImageView moodImage = view.findViewById(R.id.moodImage);
        moodImage.setImageResource(drawableId);
    }
}
