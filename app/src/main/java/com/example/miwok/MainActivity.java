package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare the TextView to be clicked on
        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView colors = (TextView) findViewById(R.id.colors);
        TextView family = (TextView) findViewById(R.id.family);
        TextView phrases = (TextView) findViewById(R.id.phrases);

        //Functions that creates a click listener
        textViewClickListenerFactory(numbers, NumbersActivity.class);
        textViewClickListenerFactory(colors, ColorsActivity.class);
        textViewClickListenerFactory(family, FamilyActivity.class);
        textViewClickListenerFactory(phrases, PhrasesActivity.class);
    }

    private void textViewClickListenerFactory(TextView textView, final Class<? extends Activity> ActivityToOpen ) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityToOpen);
                startActivity(intent);
            }
        });
    }
}