package com.example.duan1_spotify_clone.Fragment.POST_API_JSON;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterFace {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> wxecuteSignup(@Body HashMap<String,String> map);

}
