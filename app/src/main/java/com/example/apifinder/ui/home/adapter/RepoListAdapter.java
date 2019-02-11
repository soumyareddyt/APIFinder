package com.example.apifinder.ui.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.apifinder.R;
import com.example.apifinder.databinding.ItemRepoBinding;
import com.example.apifinder.ui.home.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Repository> data;

    public RepoListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    public void setData(List<Repository> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemRepoBinding repoBinding = DataBindingUtil.inflate(inflater, R.layout.item_repo, viewGroup, false);
        return new ViewHolder(repoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.setVariable(BR.repository, data.get(i));
        viewHolder.binding.setVariable(BR.imageAvatar, data.get(i).owner.avatarUrl);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRepoBinding binding;

        public ViewHolder(@NonNull ItemRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
