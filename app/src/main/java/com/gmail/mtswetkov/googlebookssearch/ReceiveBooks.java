package com.gmail.mtswetkov.googlebookssearch;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;


public class ReceiveBooks extends AsyncTaskLoader<String> {

    private String  queryString;

    public ReceiveBooks(Context context, String queryString){
        super(context);
        this.queryString =  queryString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(queryString);
    }
}
