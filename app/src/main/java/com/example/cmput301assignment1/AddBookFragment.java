package com.example.cmput301assignment1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.atomic.AtomicReference;

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
        Switch EditStatus = view.findViewById(R.id.EditStatus);
        //AtomicReference<Boolean> switchStatus = new AtomicReference<>(false);
        TextView EditStatusText = view.findViewById(R.id.EditStatusText); // New TextView for status text

        EditStatusText.setText("Unread");
        EditStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            EditStatusText.setText(isChecked ? "Read" : "Unread");

        });

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

                    //Boolean Status = switchStatus.get();
                    Boolean Status = EditStatus.isChecked();

                    listener.addBook(new Book(BookTitle, AuthorName, Genre, PublicationYear , Status));
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).UpdateBooksCount();
                    }
                })

                .create();


    }

}
