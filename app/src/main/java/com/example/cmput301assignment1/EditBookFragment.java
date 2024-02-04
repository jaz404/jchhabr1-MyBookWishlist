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

public class EditBookFragment extends DialogFragment {
    interface EditBookDialogListener {
        void editBook(Book book);
    }
    private EditBookFragment.EditBookDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditBookFragment.EditBookDialogListener) {
            listener = (EditBookFragment.EditBookDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditBookDialogListener");
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

        Book SelectedBook = getArguments().getParcelable("SelectedBook");

        if (SelectedBook != null) {
            EditBookTitle.setText(SelectedBook.getBookTitle());
            EditAuthorName.setText(SelectedBook.getAuthorName());
            EditGenre.setText(SelectedBook.getGenre());
            EditPublicationYear.setText(SelectedBook.getPublicationYear());
            EditStatus.setText(SelectedBook.getStatus());
        } else {

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit Book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String BookTitle = EditBookTitle.getText().toString();
                    String AuthorName = EditAuthorName.getText().toString();
                    String Genre = EditGenre.getText().toString();
                    String PublicationYear = EditPublicationYear.getText().toString();
                    String Status = EditStatus.getText().toString();

                    SelectedBook.setBookTitle(BookTitle);
                    SelectedBook.setAuthorName(AuthorName);
                    SelectedBook.setGenre(Genre);
                    SelectedBook.setPublicationYear(PublicationYear);
                    SelectedBook.setStatus(Status);



                    listener.editBook(SelectedBook);
                })
                .create();
    }

}
