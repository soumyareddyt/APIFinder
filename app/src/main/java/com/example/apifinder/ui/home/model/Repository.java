package com.example.apifinder.ui.home.model;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("owner")
    public Owner owner;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("stargazers_count")
    public String stargazersCount;

    public class Owner {
        @SerializedName("avatar_url")
        public String avatarUrl;
    }
}
