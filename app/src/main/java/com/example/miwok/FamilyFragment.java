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
 * Use the {@link FamilyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyFragment extends Fragment {

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

    public static FamilyFragment newInstance() {
        FamilyFragment fragment = new FamilyFragment();
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
        Integer activityColor = R.color.category_family;

        // Create lists with english and their translation for Miwok words for FAMILY and populate them
        ArrayList<String> wordsEn = new ArrayList<String>(Arrays.asList("father", "mother", "son", "daughter", "older brother", "younger brother", "older sister", "younger sister", "grandmother", "grandfather"));
        ArrayList<String> wordsMw = new ArrayList<String>(Arrays.asList("??p??", "?????a", "angsi", "tune", "taachi", "chalitti", "te???e", "kolliti", "ama", "paapa"));

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

        // Creates a list of audioIds
        ArrayList<Integer> audiosId = new ArrayList<Integer>(Arrays.asList(
                R.raw.family_father,
                R.raw.family_mother,
                R.raw.family_son,
                R.raw.family_daughter,
                R.raw.family_older_brother,
                R.raw.family_younger_brother,
                R.raw.family_older_sister,
                R.raw.family_younger_sister,
                R.raw.family_grandmother,
                R.raw.family_grandfather
        )
        );

        //Creates a list of Word objects
        final ArrayList<Word> words = new ArrayList<Word>();

        // Populate the list of Word objects with the English and Miwok's word list
        for (int index = 0; index < wordsEn.size(); index++)
            words.add(new Word(wordsEn.get(index), wordsMw.get(index), imagesId.get(index), audiosId.get(index)));

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