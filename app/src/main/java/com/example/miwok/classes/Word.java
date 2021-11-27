package com.example.miwok.classes;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private Integer mImageId;
    private Integer mAudioId;

    public Word(String defaultTranslation, String miwokTranslation, Integer audioId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioId = audioId;
    }

    public Word(String defaultTranslation, String miwokTranslation, Integer imageId, Integer audioId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageId = imageId;
        mAudioId = audioId;
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

    public Integer getmAudioId() { return mAudioId; }

    public boolean hasImage() {
        if (mImageId != null) {
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageId=" + mImageId +
                ", mAudioId=" + mAudioId +
                '}';
    }
}
