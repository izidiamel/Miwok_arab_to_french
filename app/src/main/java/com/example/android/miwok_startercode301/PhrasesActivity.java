package com.example.android.miwok_startercode301;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer media;
    ArrayList<Word> words;
    Context context;
    private MediaPlayer.OnCompletionListener mListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
            //Toast.makeText(context,"finish",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

         context=this;

        words = new ArrayList<Word>();
        words.add(new Word("إلى أين تذهــب ؟", "Où allez-vous?",R.raw.phrase_where_are_you_going));
        words.add(new Word("ما إسمك ؟",    "Comment vous vous appelez?",R.raw.phrase_what_is_your_name));
        words.add(new Word("إسمي هو...",         "je m'appelle...",R.raw.phrase_my_name_is));
        words.add(new Word("كيف حالــك ؟", "comment allez-vous ?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("أنــا بخير",     "je vais très bien",R.raw.phrase_im_feeling_good));
        words.add(new Word("هل أنت قادم ؟",       "Est-ce que tu viens?",R.raw.phrase_are_you_coming));
        words.add(new Word("نعم أنا قادم",      "Oui j'arrive",R.raw.phrase_yes_im_coming));
        words.add(new Word("أنا قادم",           "j'arrive",R.raw.phrase_im_coming));
        words.add(new Word(" هيا لنذهب",            "allons-y",R.raw.phrase_lets_go));
        words.add(new Word("تعال إلى هنا",           "viens ici",R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_phrases);


        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
                media = MediaPlayer.create(context,words.get(i).getAudioRessourceId());
                media.start();
                media.setOnCompletionListener(mListener);

            }
        });


    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (media != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            media.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            media = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }
}

