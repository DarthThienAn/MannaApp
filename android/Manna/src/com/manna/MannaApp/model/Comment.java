package com.manna.MannaApp.model;

public class Comment {
    private String author;
    private String subject;
    private String content;
    private long timestamp;

    public Comment(String author, String subject, String content, long timestamp) {
        this.author = author;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
