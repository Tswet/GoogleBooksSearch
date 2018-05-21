package com.gmail.mtswetkov.googlebookssearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowBookInfoActivity extends AppCompatActivity {

    Book book = new Book();
    TextView titleView, authorView, descriptionView, subtitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_for_view);

        Bundle b = getIntent().getExtras();
        book = (Book) b.get(DisplayActivity.SHOW_BOOK);

        titleView = findViewById(R.id.book_title_for_view);
        authorView = findViewById(R.id.book_author_for_view);
        descriptionView = findViewById(R.id.book_description_for_view);
        subtitleView = findViewById(R.id.book_subtitle_for_view);

        titleView.setText(book.title);
        authorView.setText(book.authors);
        descriptionView.setText(book.description);
        subtitleView.setText(book.subtitle);


    }
}
