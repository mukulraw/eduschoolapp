package com.eduschool.eduschoolapp.borrowedBookBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 10/13/2017.
 */

public class borrowedBookBean {

    @SerializedName("book_list")
    @Expose
    private List<BookList> bookList = null;

    public List<BookList> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookList> bookList) {
        this.bookList = bookList;
    }

}
