package com.example.retrofitlogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginInterface {

    String LOGIN_URL = "http://3.39.42.187:8080/api/";
    @POST("auth/")
    //반환타입 Call<retroRequest> - Call은 응답이 왔을때 Callback으로 불려질 타입
    //retroRequest - 요청GET에 대한 응답데이터를 받아서 DTO 객체화할 클래스 타입 지정
    //매개변수 '@Path("post") String post' - 매개변수 post가 @Path("post")를 보고 @GET 내부 {post}에 대입
    @FormUrlEncoded
    Call<retroRequest> PostRequest(
            @Field("userEmail") String userEmail,
            @Field("userPassword") String userPassword
    );
}
