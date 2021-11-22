package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miwok.adapter.WordAdapter;
import com.example.miwok.views.Word;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        // Sets the category color for this activity
        Integer activityColor = R.color.category_family;

        // Create lists with english and their translation for Miwok words for FAMILY and populate them
        ArrayList<String> wordsEn = new ArrayList<String>(Arrays.asList("father", "mother", "son", "daughter", "older brother", "younger brother", "older sister", "younger sister", "grandmother", "grandfather"));
        ArrayList<String> wordsMw = new ArrayList<String>(Arrays.asList("әpә", "әṭa", "angsi", "tune", "taachi", "chalitti", "teṭe", "kolliti", "ama", "paapa"));

        // Creates a list of imagesIds
        ArrayList<Integer> imagesId = new ArrayList<Integer>(Arrays.asList(
                R.drawable.family_father,
                R.drawable.family_mother,
                R.drawable.family_son,
                R.drawable.family_daughter,
                R.drawable.family_older_brother,
                R.drawable.family_younger_brother,
                R.drawable.family_older_sister,
                R.drawable.family_younger_sister,
                R.drawable.family_grandmother,
                R.drawable.family_grandfather
        )
        );

        //Creates a list of Word objects
        ArrayList<Word> words = new ArrayList<Word>();

        // Populate the list of Word objects with the English and Miwok's word list
        for(int index = 0; index < wordsEn.size(); index++)
            words.add(new Word(wordsEn.get(index), wordsMw.get(index), imagesId.get(index)));

        //Declare the ListView adapter
        WordAdapter adapter = new WordAdapter(this, words, activityColor);

        //Set the listView to populate with Words
        ListView listView = (ListView) findViewById(R.id.list);

        //Set the listView adapter
        listView.setAdapter(adapter);
    }
}