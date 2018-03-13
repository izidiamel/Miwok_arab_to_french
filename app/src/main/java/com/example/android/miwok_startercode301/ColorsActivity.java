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

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Context context;
    ArrayList<Word> words;
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
        words.add(new Word("أحمر", "Rouge",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("أخضر", "Vert",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("بني", "Marron",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("رمادي", "Gris",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("أسود", "Noir",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("أبيض", "Blanc",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("أصفر فاتح", "Jaune",R.drawable.color_mustard_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("أصفر ماستاردي", "Moutarde",R.drawable.color_dusty_yellow,R.raw.color_mustard_yellow));


        WordAdapter adapter = new WordAdapter(this, words,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                releaseMediaPlayer();
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
            Toast.makeText(context,"done",Toast.LENGTH_SHORT).show();
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
