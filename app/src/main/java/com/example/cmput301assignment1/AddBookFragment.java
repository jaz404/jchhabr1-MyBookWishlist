package com.example.cmput301assignment1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddBookFragment extends DialogFragment {

    interface AddBookDialogListener {
        void addBook(Book book);
    }
    private AddBookDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddBookDialogListener) {
            listener = (AddBookDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddBookDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                getLayoutInflater().inflate(R.layout.fragment_add_book, null);

        EditText EditBookTitle = view.findViewById(R.id.EditBookTitle);
        EditText EditAuthorName = view.findViewById(R.id.EditAuthorName);
        EditText EditGenre = view.findViewById(R.id.EditGenre);
        EditText EditPublicationYear = view.findViewById(R.id.EditPublicationYear);
        EditText EditStatus = view.findViewById(R.id.EditStatus);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add a Book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String BookTitle = EditBookTitle.getText().toString();
                    String AuthorName = EditAuthorName.getText().toString();
                    String Genre = EditGenre.getText().toString();
                    String PublicationYear = EditPublicationYear.getText().toString();
                    String Status = EditStatus.getText().toString();


                    listener.addBook(new Book(BookTitle, AuthorName, Genre, PublicationYear , Status));
                })
                .create();
    }

}
