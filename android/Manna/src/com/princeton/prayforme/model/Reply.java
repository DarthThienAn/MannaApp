package com.princeton.prayforme.model;

public class Reply {
    private String person;
    private String message;
    private String timestamp;

    public Reply () {}

    public Reply(String author, String message, String timestamp) {
        this.person = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Reply");
        sb.append("{person='").append(person).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
