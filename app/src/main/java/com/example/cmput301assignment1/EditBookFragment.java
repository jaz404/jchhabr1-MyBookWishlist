package com.example.cmput301assignment1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.atomic.AtomicReference;

public class EditBookFragment extends DialogFragment {
    interface EditBookDialogListener {
        void editBook(Book book);
        void DeleteBook(Book book);
    }
    private boolean once;
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
        Switch EditStatus = view.findViewById(R.id.EditStatus);
        TextView EditStatusText = view.findViewById(R.id.EditStatusText); // New TextView for status text



        Book SelectedBook = getArguments().getParcelable("SelectedBook");

//        if (SelectedBook != null) {
        EditBookTitle.setText(SelectedBook.getBookTitle());
        EditAuthorName.setText(SelectedBook.getAuthorName());
        EditGenre.setText(SelectedBook.getGenre());
        EditPublicationYear.setText(SelectedBook.getPublicationYear());
        EditStatus.setChecked(SelectedBook.getStatus());
//        if (!once) {
        //EditStatus.setOnCheckedChangeListener(null);
        EditStatusText.setText(SelectedBook.getStatus() ? "Read" : "Unread");
//            once = true;
//        }

        EditStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Update the status text when the switch state changes
            EditStatusText.setText("");
            EditStatusText.setText(isChecked ? "Read" : "Unread");
        });
//        } else {
//
//        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit Book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    String BookTitle = EditBookTitle.getText().toString();
                    String AuthorName = EditAuthorName.getText().toString();
                    String Genre = EditGenre.getText().toString();
                    String PublicationYear = EditPublicationYear.getText().toString();
                    Boolean Status = EditStatus.isChecked();

                    SelectedBook.setBookTitle(BookTitle);
                    SelectedBook.setAuthorName(AuthorName);
                    SelectedBook.setGenre(Genre);
                    SelectedBook.setPublicationYear(PublicationYear);
                    SelectedBook.setStatus(Status);



                    listener.editBook(SelectedBook);
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).UpdateBooksCount();
                    }

                })
                .setNeutralButton("Delete", (dialog, which) -> {
                    listener.DeleteBook(SelectedBook);
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).UpdateBooksCount();
                    }
                })

                .create();
    }

}
