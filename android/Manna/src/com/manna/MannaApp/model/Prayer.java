package com.manna.MannaApp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Prayer implements Parcelable {
    private String author;
    private String subject;
    private String content;
    private int timesPrayed;
    private long timestamp;

    public Prayer(String author, String subject, String content, int timesPrayed, long timestamp) {
        this.author = author;
        this.subject = subject;
        this.content = content;
        this.timesPrayed = timesPrayed;
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public void incTimesPrayed() {
        timesPrayed++;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(subject);
        parcel.writeString(content);
        parcel.writeInt(timesPrayed);
        parcel.writeLong(timestamp);
    }

    public static final Parcelable.Creator<Prayer> CREATOR = new Creator<Prayer>() {

        @Override
        public Prayer createFromParcel(Parcel parcel) {
            String author = parcel.readString();
            String subject = parcel.readString();
            String content = parcel.readString();
            int timesPrayed = parcel.readInt();
            long timestamp = parcel.readLong();
            return new Prayer(author, subject, content, timesPrayed, timestamp);
        }

        @Override
        public Prayer[] newArray(int i) {
            return new Prayer[i];
        }
    };

    private Prayer(Parcel parcel) {
        this.author = parcel.readString();
        this.subject = parcel.readString();
        this.content = parcel.readString();
        this.timesPrayed = parcel.readInt();
        this.timestamp = parcel.readLong();
    }
}
