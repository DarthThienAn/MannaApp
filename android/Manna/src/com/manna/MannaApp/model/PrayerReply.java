package com.manna.MannaApp.model;

import java.util.List;

public class PrayerReply {
    private Prayer prayer;
    private List<Reply> replies;

    public PrayerReply(Prayer prayer, List<Reply> replies) {
        this.prayer = prayer;
        this.replies = replies;
    }

    public Prayer getPrayer() {
        return prayer;
    }

    public void setPrayer(Prayer prayer) {
        this.prayer = prayer;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
