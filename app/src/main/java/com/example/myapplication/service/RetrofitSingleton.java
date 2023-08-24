package com.example.myapplication.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//참고: https://developer.android.com/training/volley/requestqueue?hl=ko#singleton
public class RetrofitSingleton {
    private static Retrofit retrofit;
    private static MemberService memberService;
    private static BoardService boardService;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    //끝에는 반드시 / 를 붙여야 됨, 그렇지 않으면 예외 발생
                    .baseUrl("http://192.168.0.3:8080/web_service_server/mobile/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static MemberService getMemberService() {
        if(memberService == null) {
            memberService = getRetrofit().create(MemberService.class);
        }
        return memberService;
    }

    public static BoardService getBoardService() {
        if(boardService == null) {
            boardService = getRetrofit().create(BoardService.class);
        }
        return boardService;
    }
}
