// Assignment 1
// jchhabr1-MyBookWishlist

package com.example.cmput301assignment1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AddBookFragment.AddBookDialogListener, EditBookFragment.EditBookDialogListener
      {

    private ArrayList<Book> DataList;
    private ListView BookList;
    private BookArrayAdapter bookAdapter;
    private int lastSelectedPosition = ListView.INVALID_POSITION;

    private TextView TotalBooks;
    private TextView TotalBooksRead;

    @Override
    public void addBook(Book book){
        bookAdapter.add(book);
        bookAdapter.notifyDataSetChanged();
    }
    @Override
    public void editBook(Book editedBook){
        if (lastSelectedPosition != ListView.INVALID_POSITION){
            DataList.set(lastSelectedPosition,editedBook);
            bookAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void DeleteBook(Book book){
        DataList.remove(book);
        bookAdapter.notifyDataSetChanged();
    }
    protected void UpdateBooksCount() {
        int totalBooks = DataList.size();
        // Counting number of books that are read:
        int totalBooksRead = 0;
        for (Book book : DataList){
            if (book.getStatus()) {
                totalBooksRead++;
            }
        }
        //Log.d("MainActivity","Read: "+totalBooks);

        //Log.d("MainActivity","Read Books Count: "+totalBooksRead);
        TotalBooks.setText("Books: "+totalBooks);
        TotalBooksRead.setText("Read: "+totalBooksRead);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String[] BookTitles = {"1984", "Theory of Everything"};
        //String[] Authors = {"George Orwell","Stephen Hawking"};
        //String[] Genre = {"dystopian","science"};
        //String[] PublicationYear = {"1949","2002"};
        boolean[] Status = {true, false};

        DataList = new ArrayList<>();


        //for (int i = 0; i < BookTitles.length; i++) {
        //    DataList.add(new Book(BookTitles[i], Authors[i],Genre[i],PublicationYear[i],Status[i]));
        //}
        BookList = findViewById(R.id.BookList);
        bookAdapter = new BookArrayAdapter(this, DataList);
        BookList.setAdapter(bookAdapter);
        FloatingActionButton fab = findViewById(R.id.buttonAddBook);
        TotalBooks = findViewById(R.id.TotalBooks);
        TotalBooksRead = findViewById(R.id.TotalBooksRead);
        // Add city
        UpdateBooksCount();
        fab.setOnClickListener(v -> {

            new AddBookFragment().show(getSupportFragmentManager(), "Add Book");
        });

    // Edit Book
    BookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("MainActivity", "onItemClick triggered");
            lastSelectedPosition = position;

            Log.d("MainActivity", "Selected position: " + lastSelectedPosition);

            EditBookFragment editBookFragment = new EditBookFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("SelectedBook", DataList.get(position));
            editBookFragment.setArguments(bundle);

            // Show the fragment
            editBookFragment.show(getSupportFragmentManager(), "Edit Book");


        }
    });

}
}