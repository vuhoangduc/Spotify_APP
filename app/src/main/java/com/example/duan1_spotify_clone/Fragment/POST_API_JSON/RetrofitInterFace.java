package com.example.duan1_spotify_clone.Fragment.POST_API_JSON;

import java.lang.reflect.Array;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterFace {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<LoginResult> wxecuteSignup(@Body HashMap<String,String> map);

    @FormUrlEncoded
    @POST("/posts_music_fv")
    Call<Post_music_fv> POST_MUSIC_FV_CALL(
            @Field("email") String email,
            @Field("music_fv") Array id_music
    );

}
