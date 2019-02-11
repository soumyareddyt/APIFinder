package com.example.apifinder.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private T binding;

    public abstract int getLayoutId();

    public abstract int getViewModelVariable();

    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setVariable(getViewModelVariable(), getViewModel());
        binding.setLifecycleOwner(this);
    }

    public T getBinding() {
        return binding;
    }
}
