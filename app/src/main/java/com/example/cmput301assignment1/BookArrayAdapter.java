package com.example.cmput301assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class BookArrayAdapter extends ArrayAdapter<Book> {
    public BookArrayAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content,
                    parent, false);
        } else {
            view = convertView;
        }
        Book book = getItem(position);
        TextView BookTitle = view.findViewById(R.id.BookTitle);
        TextView AuthorName = view.findViewById(R.id.AuthorName);
        TextView Genre = view.findViewById(R.id.Genre);
        TextView PublicationYear = view.findViewById(R.id.PublicationYear);
        TextView Status = view.findViewById(R.id.Status);

        BookTitle.setText(book.getBookTitle());
        AuthorName.setText(book.getAuthorName());
        Genre.setText(book.getGenre());
        PublicationYear.setText(book.getPublicationYear());
        Status.setText(book.getStatus());


        return view;
    }
}
