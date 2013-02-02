package com.princeton.prayforme.model;

public class Reply {
    private String person;
    private String subject;
    private String content;
    private long timestamp;

    public Reply () {}

    public Reply(String author, String subject, String content, long timestamp) {
        this.person = author;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
