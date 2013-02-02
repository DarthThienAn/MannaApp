package com.princeton.prayforme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Prayer implements Parcelable {
    private int id;
    private String person;
    private String subject;
    private String message;
    private String signature;
    private int timesPrayedFor;
    private int[] repliesList;
    private String timestamp;
    private boolean prayedFor;

    public Prayer() {}

    public Prayer(int id, String person, String subject, String message, String signature, int timesPrayedFor, int[] repliesList, String timestamp) {
        this.id = id;
        this.person = person;
        this.subject = subject;
        this.message = message;
        this.signature = signature;
        this.timesPrayedFor = timesPrayedFor;
        this.repliesList = repliesList;
        this.timestamp = timestamp;
    }

    public Prayer(int id, String person, String subject, String message, String signature, int timesPrayedFor, int[] repliesList, String timestamp, boolean prayedFor) {
        this.id = id;
        this.person = person;
        this.subject = subject;
        this.message = message;
        this.signature = signature;
        this.timesPrayedFor = timesPrayedFor;
        this.repliesList = repliesList;
        this.timestamp = timestamp;
        this.prayedFor = prayedFor;
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

    public int[] getRepliesList() {
        return repliesList;
    }

    public void setRepliesList(int[] repliesList) {
        this.repliesList = repliesList;
    }

    public int getTimesReplied() {
        return (repliesList == null) ? 0 : repliesList.length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(person);
        parcel.writeString(subject);
        parcel.writeString(message);
        parcel.writeString(signature);
        parcel.writeInt(timesPrayedFor);
        parcel.writeInt(repliesList == null ? 0 : repliesList.length);
        parcel.writeIntArray(repliesList == null ? new int[0] : repliesList);
        parcel.writeString(timestamp);
        boolean[] booleans = { prayedFor };
        parcel.writeBooleanArray(booleans);
    }

    public static final Parcelable.Creator<Prayer> CREATOR = new Creator<Prayer>() {

        @Override
        public Prayer createFromParcel(Parcel parcel) {
            int prayerId = parcel.readInt();
            String person = parcel.readString();
            String subject = parcel.readString();
            String content = parcel.readString();
            String signature = parcel.readString();
            int timesPrayed = parcel.readInt();
            int repliesLength = parcel.readInt();
            int[] replies = new int[repliesLength];
            parcel.readIntArray(replies);
            String timestamp = parcel.readString();
            boolean[] booleans = new boolean[1];
            parcel.readBooleanArray(booleans);
            return new Prayer(prayerId, person, subject, content, signature, timesPrayed, replies, timestamp, booleans[0]);
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
        sb.append("{id=").append(id);
        sb.append(", person='").append(person).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", signature='").append(signature).append('\'');
        sb.append(", timesPrayedFor=").append(timesPrayedFor);
        sb.append(", repliesList=").append(repliesList == null ? "null" : "{");
        for (int i = 0; repliesList != null && i < repliesList.length; ++i)
            sb.append(repliesList[i]).append(", ");
        sb.append("}, timestamp='").append(timestamp).append('\'');
        sb.append(", prayedFor=").append(prayedFor);
        sb.append('}');
        return sb.toString();
    }
}
