package com.example.myapplication.service;

import com.example.myapplication.dto.LoginResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MemberService {
    @GET("login")
    Call<LoginResult> login(@Query("mid") String mid, @Query("mpassword") String mpassword);
}
