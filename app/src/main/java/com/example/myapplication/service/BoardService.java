package com.example.myapplication.service;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.Board;
import com.example.myapplication.dto.WriteResult;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BoardService {
    @GET("getBoardList")
    Call<List<Board>> getBoardList();

    static void loadImage(Context context, int bno, ImageView imageView) {
        //동일한 URL 이미지가 캐시에 있다면 서버 요청을 하지 않음, 기본적으로 앱 캐시 폴더에 250MB까지 캐싱
        Glide.with(context).load(NetworkInfo.BASE_URL + "filedownload?bno=" + bno).into(imageView);
    }

    @POST("writeBoard")
    @Multipart
    Call<WriteResult> writeBoard(@Part List<MultipartBody.Part> partList);
}
