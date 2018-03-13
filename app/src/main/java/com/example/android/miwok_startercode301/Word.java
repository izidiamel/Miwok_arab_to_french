package com.example.android.miwok_startercode301;

/**
 * Created by Home on 04/03/2018.
 */

public class Word {

    private String mMiwokTranslation;
    private int mImageRessourceId;
    private int mAudioResourceId;
    private String mDefaultTranslation;
    private static final  int NO_IMAGE_PROVIDED = -1;
    public Word( String defaultWord , String miwokWord,int audioId){
        mMiwokTranslation=miwokWord;
        mDefaultTranslation=defaultWord;
        mImageRessourceId=NO_IMAGE_PROVIDED;
        mAudioResourceId=audioId;
    }
    public Word( String defaultWord , String miwokWord , int imageId,int audioId){
        mImageRessourceId=imageId;
        mMiwokTranslation=miwokWord;
        mDefaultTranslation=defaultWord;
        mAudioResourceId=audioId;
    }
    public boolean hasImageRessources(){
        if(mImageRessourceId ==-1){
            return false;
        }
        else
        {
            return true;
        }
    }
    public int    getImageRessourceId(){
        return mImageRessourceId;
    }
    public int    getAudioRessourceId(){
        return mAudioResourceId;
    }
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
}
