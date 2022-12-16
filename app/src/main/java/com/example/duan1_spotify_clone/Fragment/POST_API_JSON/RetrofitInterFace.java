package com.example.duan1_spotify_clone.Fragment.POST_API_JSON;

import com.example.duan1_spotify_clone.DTO.User;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterFace {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<LoginResult> wxecuteSignup(@Body HashMap<String,String> map);
    @GET("/get_api_user")
    Call<List<User>> USER_CALL();
    @POST("/posts_music_fv")
    Call<Post_music_fv> POST_MUSIC_FV_CALL(
            @Body HashMap<String,String> map
    );

}
