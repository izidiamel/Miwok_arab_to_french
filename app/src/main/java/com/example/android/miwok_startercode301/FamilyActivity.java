package com.example.android.miwok_startercode301;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
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
        words.add(new Word("أب",        "père",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("أم",        "mère",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("إبن",           "fils",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("إبنة",      "fille",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("أخ أكبر", "grand frère ",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("أخ أصغر","petit frère",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("أخت كبرى",   "grande soeur",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("أخت صغرى", "petite soeur",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("جدة",   "grand mère",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("جد",   "grand père",R.drawable.family_grandfather,R.raw.family_grandfather));




        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                mediaPlayer = MediaPlayer.create(context,words.get(i).getAudioRessourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mListener);

            }
        });

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
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }
}
