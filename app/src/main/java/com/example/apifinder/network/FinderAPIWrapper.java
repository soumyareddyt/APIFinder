package com.example.apifinder.network;

import com.example.apifinder.ui.home.model.RepoResponse;
import com.example.apifinder.ui.home.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinderAPIWrapper {

    private static final String BASE_URL = "https://api.github.com/";
    private static FinderAPIWrapper API_WRAPPER;
    private final FinderAPI finderAPI;

    public static FinderAPIWrapper getInstance() {
        if (API_WRAPPER == null) {
            API_WRAPPER = new FinderAPIWrapper();
        }
        return API_WRAPPER;
    }

    private FinderAPIWrapper() {
        Retrofit build = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        finderAPI = build.create(FinderAPI.class);
    }

    public Call<RepoResponse> searchRepo(String q, Callback<RepoResponse> callback) {
        Call<RepoResponse> call = finderAPI.getRepositories(q, "stars");
        call.enqueue(callback);
        return call;
    }
}
