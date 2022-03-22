package com.example.retrofitlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText join_email, join_password;
    private Button joinBtn;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        join_email = (EditText) findViewById(R.id.join_email);
        join_password = (EditText) findViewById(R.id.join_password);

        joinBtn = (Button) findViewById(R.id.joinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEmail = join_email.getText().toString().trim();
                final String userPassword = join_password.getText().toString().trim();
                //.trim() 함수는 오른쪽 왼쪽의 공백을 제거해주는데 유용하게 쓰인다.

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LoginInterface.LOGIN_URL)
                        //baseUrl을 등록하는 것으로 반드시 마지막은 / 이여야한다.
                        .addConverterFactory(GsonConverterFactory.create())
                        //GsonConverterFactory.create()는 JSON으로 변환해줄 변환기이다.
                        .build();

                LoginInterface retroRequest = retrofit.create(LoginInterface.class);
                // retrofit 인스턴스로 인터페이스 객체 구현 *반드시 인터페이스이어야 함.

                LoginInterface api = retrofit.create(LoginInterface.class);
                Call<retroRequest> call = api.PostRequest(userEmail, userPassword);
                call.enqueue(new Callback<com.example.retrofitlogin.retroRequest>() {
                    //enqueue로 비동기 통신 실행 및 통신 완료 후 이벤트 처리를 위한 call back 리스너 등
                    private static final String TAG = "";

                    @Override
                    public void onResponse(Call<com.example.retrofitlogin.retroRequest> call, Response<com.example.retrofitlogin.retroRequest> response) {
                    //정상적으로 통신이 성공한 경우, ui작업 가능 및 메인스레에서 작업하는 부분
                        if (response.isSuccessful()) {
                            com.example.retrofitlogin.retroRequest retroRequest1 = response.body();
                            Log.d(TAG, "onResponse: 성공, 결과 \n" + retroRequest1.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.retrofitlogin.retroRequest> call, Throwable t) {
                        //오류가 발생한 경우 / 통신이 실패한 경우
                        Log.d(TAG, "onFailure : " + t.getMessage());
                    }
                });
            }
        });
    }
}