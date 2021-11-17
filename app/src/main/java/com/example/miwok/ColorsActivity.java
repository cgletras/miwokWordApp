package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miwok.adapter.WordAdapter;
import com.example.miwok.views.Word;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        // Create lists with english and their translation for Miwok words for COLORS and populate them
        ArrayList<String> wordsEn = new ArrayList<String>(Arrays.asList("Red", "Green", "Brown", "Gray", "Black", "White", "Dusty Yellow", "Mustard Yellow"));
        ArrayList<String> wordsMw = new ArrayList<String>(Arrays.asList("Weṭeṭṭi", "Chokokki", "Takaakki", "Topoppi", "Kululli", "Kelelli", "Topiisә", "Chiwiiṭә"));

        //Creates a list of Word objects
        ArrayList<Word> words = new ArrayList<Word>();

        // Populate the list of Word objects with the English and Miwok's word list
        for(int index = 0; index < wordsEn.size(); index++)
            words.add(new Word(wordsEn.get(index), wordsMw.get(index)));

        //Declare the ListView adapter
        WordAdapter adapter = new WordAdapter(this, words);

        //Set the listView to populate with Words
        ListView listView = (ListView) findViewById(R.id.list);

        //Set the listView adapter
        listView.setAdapter(adapter);
    }
}