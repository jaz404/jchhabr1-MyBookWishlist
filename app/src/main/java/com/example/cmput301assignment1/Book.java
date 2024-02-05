package com.example.cmput301assignment1;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;




public class Book implements Parcelable {
    // Book class Represents a single entity book with attributes: booktitle, authorname, genre, pulicationyear, status
    // Also implements interface parcelable for transfer of books (entities) between the different components of the app
    // Attributes:
    private String BookTitle;
    private String AuthorName;
    private String Genre;

    private String PublicationYear;
    //private String Status;
    private Boolean Status;

    // Constructor of Book Class:
    public Book(String BookTitle, String AuthorName, String Genre, String PublicationYear ,Boolean Status){
        this.BookTitle = BookTitle;
        this.AuthorName = AuthorName;
        this.Genre = Genre;
        this.PublicationYear = PublicationYear;
        this.Status = Status;
    }

    // getters and setters


    public String getBookTitle() {
        return BookTitle;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getGenre() {
        return Genre;
    }

    public String getPublicationYear() {
        return PublicationYear;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setPublicationYear(String publicationYear) {
        PublicationYear = publicationYear;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    protected Book(Parcel book) {
        BookTitle = book.readString();
        AuthorName = book.readString();
        Genre =book.readString();
        PublicationYear = book.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Status = book.readBoolean();
        }

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel book) {
            return new Book(book);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(BookTitle);
        dest.writeString(AuthorName);
        dest.writeString(Genre);
        dest.writeString(PublicationYear);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(Status);
        }
    }

}
