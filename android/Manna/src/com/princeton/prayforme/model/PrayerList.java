package com.princeton.prayforme.model;

import java.util.List;

public class PrayerList {
    List<Prayer> prayers;

    public PrayerList() {}

    public PrayerList(List<Prayer> prayers) {
        this.prayers = prayers;
    }

    public List<Prayer> getPrayers() {
        return prayers;
    }

    public void setPrayers(List<Prayer> prayers) {
        this.prayers = prayers;
    }
}
