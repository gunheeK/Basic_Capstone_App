package com.peachjelly99.tuto1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("sql/meka/table/final")
    Call<List<Post>> getPosts();

    @POST("sql/meka/insert")
    Call<Post> createPost(@Body Post post);
}
