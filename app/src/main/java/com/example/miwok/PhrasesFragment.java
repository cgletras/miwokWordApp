package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miwok.adapter.WordAdapter;
import com.example.miwok.classes.Word;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhrasesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhrasesFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public static PhrasesFragment newInstance(String param1, String param2) {
        PhrasesFragment fragment = new PhrasesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_word_list, container, false);

        // Sets the Audio Manager for Audio Focus impl
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Sets the category color for this activity
        Integer activityColor = R.color.category_phrases;

        // Create lists with english and their translation for Miwok words for PHRASES and populate them
        ArrayList<String> wordsEn = new ArrayList<String>(Arrays.asList("Where are you going?", "What is your name?", "My name is...", "How are you feeling?", "I’m feeling good.", "Are you coming?", "Yes, I’m coming.", "I’m coming.", "Let’s go.", "Come here."));
        ArrayList<String> wordsMw = new ArrayList<String>(Arrays.asList("minto wuksus", "tinnә oyaase'nә", "oyaaset...", "michәksәs?", "kuchi achit", "әәnәs'aa?", "hәә’ әәnәm", "әәnәm", "yoowutis", "әnni'nem"));

        // Creates a list of audioIds
        ArrayList<Integer> audiosId = new ArrayList<Integer>(Arrays.asList(
                R.raw.phrase_where_are_you_going,
                R.raw.phrase_what_is_your_name,
                R.raw.phrase_my_name_is,
                R.raw.phrase_how_are_you_feeling,
                R.raw.phrase_im_feeling_good,
                R.raw.phrase_are_you_coming,
                R.raw.phrase_yes_im_coming,
                R.raw.phrase_im_coming,
                R.raw.phrase_lets_go,
                R.raw.phrase_come_here
        )
        );

        //Creates a list of Word objects
        ArrayList<Word> words = new ArrayList<Word>();

        // Populate the list of Word objects with the English and Miwok's word list
        for(int index = 0; index < wordsEn.size(); index++)
            words.add(new Word(wordsEn.get(index), wordsMw.get(index), audiosId.get(index)));

        //Declare the ListView adapter
        WordAdapter adapter = new WordAdapter(getActivity(), words, activityColor);

        //Set the listView to populate with Words
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //Set the listView adapter
        listView.setAdapter(adapter);

        // Set the ListView click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getmAudioId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}