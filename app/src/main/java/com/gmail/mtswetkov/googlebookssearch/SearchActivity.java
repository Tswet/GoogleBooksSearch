package com.gmail.mtswetkov.googlebookssearch;

import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity
        implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {

    public static final String TAG = SearchActivity.class.getSimpleName();
    private TextView noInfoView;
    private EditText searchInput;
    public static final String BOOK = "BOOK" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        noInfoView = findViewById(R.id.no_info);
        searchInput = findViewById(R.id.search_input);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchBooks(View view) {
        String queryString = searchInput.getText().toString();

        if (queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

        } else {
            Toast.makeText(getApplicationContext(), "Enter the search info", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new ReceiveBooks(this, args.getString("queryString"));
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<String> loader, String data) {
        Intent i = new Intent(SearchActivity.this, DisplayActivity.class);
        try {
            i.putExtra(BOOK, data.toString());
            startActivity(i);
            getSupportLoaderManager().destroyLoader(0);
        } catch (Exception ex) {
            noInfoView.getResources().getString(R.string.no_item_found);
            ex.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<String> loader) {
    }
}