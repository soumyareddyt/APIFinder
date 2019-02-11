package com.example.apifinder.ui.home.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.apifinder.BR;
import com.example.apifinder.R;
import com.example.apifinder.databinding.ActivityHomeBinding;
import com.example.apifinder.ui.BaseActivity;
import com.example.apifinder.ui.home.adapter.RepoListAdapter;
import com.example.apifinder.ui.home.viewmodel.HomeViewModel;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {

    private HomeViewModel homeViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public int getViewModelVariable() {
        return BR.homeViewModel;
    }

    @Override
    public HomeViewModel getViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        return homeViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RepoListAdapter repoListAdapter = new RepoListAdapter(this);
        getBinding().rcRepos.setAdapter(repoListAdapter);

        getBinding().etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                homeViewModel.getRepositories(v.getText().toString());
                getBinding().etSearch.setText("");
                return true;
            }
            return false;
        });

        homeViewModel.data.observe(this, data -> {
            if (data == null || data.size() == 0)
                Toast.makeText(HomeActivity.this, "No Results Found!!", Toast.LENGTH_SHORT).show();
            else
                repoListAdapter.setData(data);
        });

        homeViewModel.errorEvent.observe(this, aBoolean -> {
            Toast.makeText(HomeActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
        });
    }
}
