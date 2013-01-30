package com.manna.MannaApp.list;

import android.content.Context;
import android.view.View;

public abstract class ListItem<T> {

    protected Context context;
    protected T model;

    public ListItem(Context context) {
        this.context = context;
    }

    public ListItem(Context context, T model) {
        this.context = context;
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

        if (model != null ? !model.equals(that.model) : that.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return model != null ? model.hashCode() : 0;
    }
}
