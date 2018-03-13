package com.example.android.miwok_startercode301;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
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
        words.add(new Word("واحد", "un",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("إثنان", "deux",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("ثلاثة", "trois",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("أربعة", "quatre",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("خمسة", "sinq",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("ستة", "six",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("سبعة", "sept",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("ثمانية", "huit",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("تسعة", "neuf",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("عشرة", "dix",R.drawable.number_ten,R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);


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


//String resourceName = context.getResources().getResourceEntryName(words.get(i).getAudioRessourceId());
//String filename = "android.resource://" + context.getPackageName() + "/raw/"+resourceName;
//mediaPlayer.reset();
//try { mediaPlayer.setDataSource(context, Uri.parse(filename)); } catch (Exception e) {}
//try { mediaPlayer.prepare();} catch (Exception e) {}