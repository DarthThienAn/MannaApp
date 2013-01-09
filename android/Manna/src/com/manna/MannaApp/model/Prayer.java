package com.manna.MannaApp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Prayer implements Parcelable {
    private String author;
    private String content;
    private int timesPrayed;

    public Prayer(String author, String content, int timesPrayed) {
        this.author = author;
        this.content = content;
        this.timesPrayed = timesPrayed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTimesPrayed() {
        return timesPrayed;
    }

    public void setTimesPrayed(int timesPrayed) {
        this.timesPrayed = timesPrayed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeInt(timesPrayed);
    }

    public static final Parcelable.Creator<Prayer> CREATOR = new Creator<Prayer>() {

        @Override
        public Prayer createFromParcel(Parcel parcel) {
            String author = parcel.readString();
            String content = parcel.readString();
            int timesPrayed = parcel.readInt();
            return new Prayer(author, content, timesPrayed);
        }

        @Override
        public Prayer[] newArray(int i) {
            return new Prayer[i];
        }
    };

    private Prayer(Parcel parcel) {
        this.author = parcel.readString();
        this.content = parcel.readString();
        this.timesPrayed = parcel.readInt();
    }
}
