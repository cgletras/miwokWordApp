package com.example.miwok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.miwok.R;
import com.example.miwok.views.Word;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(@NonNull Context context, @NonNull List<Word> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Check if view is being reused
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        //get the object in the list
        Word currentWord = getItem(position);

        // Find the text views
        TextView enTextView = listItemView.findViewById(R.id.tv_english_word);
        TextView mwTextView = listItemView.findViewById(R.id.tv_miwok_word);

        // Set info from class into view
        enTextView.setText(currentWord.getmDefaultTranslation());
        mwTextView.setText(currentWord.getmMiwokTranslation());

        return listItemView;
    }
}
