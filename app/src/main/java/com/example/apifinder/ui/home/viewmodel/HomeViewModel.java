package com.example.apifinder.ui.home.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.apifinder.network.FinderAPIWrapper;
import com.example.apifinder.ui.BaseViewModel;
import com.example.apifinder.ui.home.model.RepoResponse;
import com.example.apifinder.ui.home.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {

    public MutableLiveData<List<Repository>> data = new MutableLiveData<>();

    public MutableLiveData<Boolean> errorEvent = new MutableLiveData<>();

    public MutableLiveData<Integer> showProgressEvent = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        showProgressEvent.setValue(View.GONE);
    }

    /**
     * Call the network call to fetch the user repositories based on the query
     *
     * @param query
     */
    public void getRepositories(String query) {
        showProgressEvent.setValue(View.VISIBLE);
        FinderAPIWrapper.getInstance().searchRepo(query, new Callback<RepoResponse>() {
            @Override
            public void onResponse(Call<RepoResponse> call, Response<RepoResponse> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    data.setValue(response.body().items);
                } else
                    errorEvent.setValue(true);
                showProgressEvent.setValue(View.GONE);
            }

            @Override
            public void onFailure(Call<RepoResponse> call, Throwable t) {
                errorEvent.setValue(true);
                showProgressEvent.setValue(View.GONE);
            }
        });
    }

}
