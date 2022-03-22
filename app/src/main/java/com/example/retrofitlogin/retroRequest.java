package com.example.retrofitlogin;

import com.google.gson.annotations.SerializedName;

public class retroRequest {
// DTO 모델 - retroRequest Class 선언
    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPassword")
    private String userPassword;

    @Override
    public String toString() {
        return "retroRequest {" +
                ", userEmail = " + userEmail +
                ", userPassword = " + userPassword +
                '}';

        // JSON 데이터의 속성명과 변수명 + 타입(ex String,Int,Boolean) 일치 필수
        //      : JSON - @SerializedName("속성명")으로 속성명 일치시켜주면 변수명 다르게도 가능


    }
}
