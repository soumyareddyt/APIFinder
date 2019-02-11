package com.example.apifinder.network;

import com.example.apifinder.ui.home.model.RepoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinderAPI {
    @GET("/search/repositories")
    Call<RepoResponse> getRepositories(@Query("q") String query, @Query("sort") String sort);
}
