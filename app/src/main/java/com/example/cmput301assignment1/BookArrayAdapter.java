package com.example.cmput301assignment1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class BookArrayAdapter extends ArrayAdapter<Book> {
    // BookArrayAdapter class extends parent class ArrayAdapter using Book class as underlying data
    // Displays list of books in the ListView
    // Constructor:
    public BookArrayAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent) {
        View view;
        if (convertView == null) { // creates a view if no view can be recycled
            view = LayoutInflater.from(getContext()).inflate(R.layout.content,
                    parent, false);
        } else {
            view = convertView; // recycles the view
        }
        Book book = getItem(position);
        TextView BookTitle = view.findViewById(R.id.BookTitle);
        TextView AuthorName = view.findViewById(R.id.AuthorName);
        TextView Genre = view.findViewById(R.id.Genre);
        TextView PublicationYear = view.findViewById(R.id.PublicationYear);
        //Switch Status = view.findViewById(R.id.Status);
        TextView StatusText = view.findViewById(R.id.StatusText);

        BookTitle.setText(book.getBookTitle());
        AuthorName.setText(book.getAuthorName());
        Genre.setText(book.getGenre());
        PublicationYear.setText(book.getPublicationYear());

        //Status.setChecked(book.getStatus());
        StatusText.setText(book.getStatus() ? "Read" : "Unread");
        return view;
    }
}
