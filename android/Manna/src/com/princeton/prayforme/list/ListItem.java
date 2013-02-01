package com.princeton.prayforme.list;

import android.view.View;

public abstract class ListItem<T> {

    protected T model;

    public ListItem(T model) {
        this.model = model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract ListItemType getType();

    public abstract int getResourceId();

    public abstract void populate(View view);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListItem)) return false;

        ListItem that = (ListItem) o;

        return !(model != null ? !model.equals(that.model) : that.model != null);
    }

    @Override
    public int hashCode() {
        return model != null ? model.hashCode() : 0;
    }
}
