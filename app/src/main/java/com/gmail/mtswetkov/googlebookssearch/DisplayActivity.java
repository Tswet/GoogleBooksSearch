package com.gmail.mtswetkov.googlebookssearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayActivity extends AppCompatActivity {

    public final static String SHOW_BOOK = "SHOW_BOOK";

    public ArrayList<Book> books = new ArrayList<>();
    String jSonSring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        RecyclerView recyclerView = findViewById(R.id.books_list);
        Bundle b = getIntent().getExtras();
        jSonSring = (String) b.get(SearchActivity.BOOK);

        try {
            JSONObject jsonObject = new JSONObject(jSonSring);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String title = "";
            String authors = "";
            String description = "";
            String subtitle = "";

            while (i < itemsArray.length() || (authors == null && title == null)) {

                try {
                    JSONObject book = itemsArray.getJSONObject(i);
                    JSONObject bookInfo = book.getJSONObject("volumeInfo");

                    try {
                        title = bookInfo.getString("title");
                        authors = bookInfo.getString("authors");
                        subtitle = bookInfo.getString("subtitle");
                        description = bookInfo.getString("description");
                        books.add(new Book(title, authors, subtitle, description));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    i++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        BookAdapter adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);
    }

    public class BookAdapter extends RecyclerView.Adapter<ViewHoolder> {

        private LayoutInflater inflater;
        private ArrayList<Book> books;

        BookAdapter(ArrayList<Book> books) {
            this.books = books;
            this.inflater = LayoutInflater.from(DisplayActivity.this);
        }


        public ViewHoolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_for_list, parent, false);
            return new ViewHoolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHoolder holder, int position) {
            Book book = books.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.onClick(view);
                }
            });

            holder.bind(book);
        }

        @Override
        public int getItemCount() {
            return books.size();
        }
    }

    public class ViewHoolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView itemTitle, itemAuthor;
        Book myBook = new Book();

        ViewHoolder(View view) {
            super(view);
            itemTitle = view.findViewById(R.id.booksName);
            itemAuthor = view.findViewById(R.id.booksWrighter);
            itemView.setOnClickListener(this);
        }

        void bind(Book book) {
            itemTitle.setText(book.title);
            this.myBook = book;
            if (book.authors == "") {
                itemAuthor.setText("NONE");
            } else {
                itemAuthor.setText(book.authors);
            }
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(DisplayActivity.this, ShowBookInfoActivity.class );
            i.putExtra(SHOW_BOOK, myBook);
            startActivity(i);
        }
    }
}
