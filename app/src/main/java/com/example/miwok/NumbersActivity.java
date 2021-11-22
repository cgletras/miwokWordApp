package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.adapter.WordAdapter;
import com.example.miwok.views.Word;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        // Sets the category color for this activity
        Integer activityColor = R.color.category_numbers;

        // Create lists with english and their translation for Miwok words for NUMBERS and populate them
        ArrayList<String> wordsEn = new ArrayList<String>(Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"));
        ArrayList<String> wordsMw = new ArrayList<String>(Arrays.asList("Lutti", "Otiiko", "Tolookosu", "Oyyisa", "Massokka", "Temmokka", "Kenekaku", "Kawinta", "Wo'e", "Na'aacha"));

        // Creates a list of imagesIds
        ArrayList<Integer> imagesId = new ArrayList<Integer>(Arrays.asList(
                R.drawable.number_one,
                R.drawable.number_two,
                R.drawable.number_three,
                R.drawable.number_four,
                R.drawable.number_five,
                R.drawable.number_six,
                R.drawable.number_seven,
                R.drawable.number_eight,
                R.drawable.number_nine,
                R.drawable.number_ten
        )
        );

        //Creates a list of Word objects
        ArrayList<Word> words = new ArrayList<Word>();

        // Populate the list of Word objects with the English and Miwok's word list
        for (int index = 0; index < wordsEn.size(); index++)
            words.add(new Word(wordsEn.get(index), wordsMw.get(index), imagesId.get(index)));

        //Declare the ListView adapter
        WordAdapter adapter = new WordAdapter(this, words, activityColor);

        //Set the listView to populate with Words
        ListView listView = (ListView) findViewById(R.id.list);

        //Set the listView adapter
        listView.setAdapter(adapter);
    }
}