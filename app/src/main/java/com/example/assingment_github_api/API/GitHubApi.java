package com.example.assingment_github_api.API;

import com.example.assingment_github_api.Model.Repository;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {
    @GET("users/{user}/repos")
    Call<ArrayList<Repository>> getRepositories(@Path("user") String user);
}