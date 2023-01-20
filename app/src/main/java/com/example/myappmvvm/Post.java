package com.example.myappmvvm;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;

    private int id;

    private String title;

    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}
