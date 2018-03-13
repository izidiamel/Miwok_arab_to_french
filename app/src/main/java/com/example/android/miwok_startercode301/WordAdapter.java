package com.example.android.miwok_startercode301;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Home on 04/03/2018.
 */

public class WordAdapter  extends ArrayAdapter<Word> {
    private int mColorResourceId;
    private Word currentWord;

    //private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    public WordAdapter(Activity context, ArrayList<Word> word_list, int color) {

        super(context, 0, word_list);
        mColorResourceId = color;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        int color= ContextCompat.getColor(getContext(),mColorResourceId);

        currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_word);
        miwokTextView.setText(currentWord.getMiwokTranslation());
        miwokTextView.setTextColor(color);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_word);
        defaultTextView.setText(currentWord.getDefaultTranslation());
        defaultTextView.setTextColor(color);


        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImageRessources()) {
            iconView.setImageResource(currentWord.getImageRessourceId());
           iconView.setVisibility(View.VISIBLE);
        }
        else{
            iconView.setVisibility(View.GONE);
        }


        View textContainer =(LinearLayout) listItemView.findViewById(R.id.text_container) ;
        textContainer.setBackgroundResource(mColorResourceId);

        return listItemView;

    }




}
