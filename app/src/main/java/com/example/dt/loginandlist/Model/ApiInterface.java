package com.example.dt.loginandlist.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("repos?page=1&per_page=15%20")
    Call<ArrayList<Course>> getCourses();

}
