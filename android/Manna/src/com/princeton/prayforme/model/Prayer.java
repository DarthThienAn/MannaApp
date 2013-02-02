package com.princeton.prayforme.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Prayer implements Parcelable {
    private String person;
    private String subject;
    private String message;
    private String signature;
    private int timesPrayedFor;
    private int[] replies;
    private String timestamp;
    private boolean prayedFor;

    public Prayer() {}

    public Prayer(String person, String subject, String message, String signature, int timesPrayedFor, int[] replies, String timestamp, boolean prayedFor) {
        this.person = person;
        this.subject = subject;
        this.message = message;
        this.signature = signature;
        this.timesPrayedFor = timesPrayedFor;
        this.replies = replies;
        this.timestamp = timestamp;
        this.prayedFor = prayedFor;
//        Log.d("PPFM", "1");
    }

    public Prayer(String person, String subject, String message, String signature, int timesPrayedFor, int[] replies, String timestamp) {
        this.person = person;
        this.subject = subject;
        this.message = message;
        this.signature = signature;
        this.timesPrayedFor = timesPrayedFor;
        this.replies = replies;
        this.timestamp = timestamp;
//        Log.d("PPFM", "2");
    }

    public Prayer(String subject, String message, String signature, int timesPrayedFor, int[] replies, String timestamp) {
        this.subject = subject;
        this.message = message;
        this.signature = signature;
        this.timesPrayedFor = timesPrayedFor;
        this.replies = replies;
        this.timestamp = timestamp;
        person = "Author";
//        Log.d("PPFM", "3");
    }

    public boolean isPrayedFor() {
        return prayedFor;
    }

    public void setPrayedFor(boolean prayedFor) {
        this.prayedFor = prayedFor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getTimesPrayedFor() {
        return timesPrayedFor;
    }

    public void setTimesPrayedFor(int timesPrayedFor) {
        this.timesPrayedFor = timesPrayedFor;
    }

    public void incTimesPrayed() {
        timesPrayedFor++;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int[] getReplies() {
        return replies;
    }

    public void setReplies(int[] replies) {
        this.replies = replies;
    }

    public int getTimesReplied() {
        return replies.length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(person);
        parcel.writeString(subject);
        parcel.writeString(message);
        parcel.writeString(signature);
        parcel.writeInt(timesPrayedFor);
        parcel.writeInt(replies.length);
        parcel.writeIntArray(replies);
        parcel.writeString(timestamp);
        boolean[] booleans = { prayedFor };
        parcel.writeBooleanArray(booleans);
    }

    public static final Parcelable.Creator<Prayer> CREATOR = new Creator<Prayer>() {

        @Override
        public Prayer createFromParcel(Parcel parcel) {
            String author = parcel.readString();
            String subject = parcel.readString();
            String content = parcel.readString();
            String signature = parcel.readString();
            int timesPrayed = parcel.readInt();
            int[] replies = new int[parcel.readInt()];
            parcel.readIntArray(replies);
            String timestamp = parcel.readString();
            boolean[] booleans = new boolean[1];
            parcel.readBooleanArray(booleans);
            return new Prayer(author, subject, content, signature, timesPrayed, replies, timestamp, booleans[0]);
        }

        @Override
        public Prayer[] newArray(int i) {
            return new Prayer[i];
        }
    };

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Prayer");
        sb.append("{person='").append(person).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", signature='").append(signature).append('\'');
        sb.append(", timesPrayedFor=").append(timesPrayedFor);
        sb.append(", replies=").append(replies == null ? "null" : "{");
        for (int i : replies)
            sb.append(replies[i]).append(", ");
        sb.append("}, timestamp=").append(timestamp);
        sb.append(", prayedFor=").append(prayedFor);
        sb.append('}');
        return sb.toString();
    }
}
