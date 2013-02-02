package com.princeton.prayforme.model;

public class PrayerPostResponse {
    private int status;
    private String message;
    private int prayerId;

    public PrayerPostResponse() {}

    public PrayerPostResponse(int status, String message, int prayerId) {
        this.status = status;
        this.message = message;
        this.prayerId = prayerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrayerId() {
        return prayerId;
    }

    public void setPrayerId(int prayerId) {
        this.prayerId = prayerId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PrayerPostResponse");
        sb.append("{status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", prayerId=").append(prayerId);
        sb.append('}');
        return sb.toString();
    }
}
