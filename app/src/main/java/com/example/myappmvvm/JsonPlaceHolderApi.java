package com.example.myappmvvm;

import android.app.LauncherActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post> response){
        if (!response.isSuccessful()){
            tvText.setText("Code: " + response.code());
            return;
        }

        List<Post> posts = response.body();

        for (Post post : posts){
            LauncherActivity.ListItem = new LauncherActivity.ListItem(post.getId(), post.getTitle(), post.getText());
            listItems.add(listItem);
        }

        rcView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        tvText.setText(t.getMessage());
    }
}

