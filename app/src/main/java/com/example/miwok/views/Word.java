package com.example.miwok.views;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private Integer mImageId;

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, Integer imageId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageId = imageId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public Integer getmImageId() {
        return mImageId;
    }

    public boolean hasImage() {
        if (mImageId != null) {
            return true;
        } else return false;
    }
}
