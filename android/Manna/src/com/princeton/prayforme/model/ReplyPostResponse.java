package com.princeton.prayforme.model;

public class ReplyPostResponse {
    private int status;
    private int replyId;

    public ReplyPostResponse() {}

    public ReplyPostResponse(int status, int replyId) {
        this.status = status;
        this.replyId = replyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ReplyPostResponse");
        sb.append("{status=").append(status);
        sb.append(", replyId=").append(replyId);
        sb.append('}');
        return sb.toString();
    }
}
