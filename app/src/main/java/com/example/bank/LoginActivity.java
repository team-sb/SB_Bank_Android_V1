package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private TextView tv_signUpButton;
    private ImageButton ib_next;


    private EditText et_putID;
    private EditText et_putPW;
    private RetrofitClient retrofitClient;
    private ServerAPI serverAPI;

    public static String username;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_putID = (EditText) findViewById(R.id.et_putID);
        et_putPW = (EditText) findViewById(R.id.et_putPW);

        tv_signUpButton = (TextView) findViewById(R.id.tv_signUpButton);
        tv_signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        ib_next = (ImageButton) findViewById(R.id.ib_next);
        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

    }

    private void Login() {
        username = et_putID.getText().toString();
        password = et_putPW.getText().toString();

        hideKeyboard();

        if(username.trim().length() == 0 || password.trim().length() == 0 || username == null || password == null) {
            Toast.makeText(LoginActivity.this, "올바른 로그인 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            LoginResponse();
        }
    }

    private void hideKeyboard() // 키보드 숨기기
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_putID.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_putPW.getWindowToken(), 0);
    }

    public void LoginResponse() {
        String username = et_putID.getText().toString().trim();
        String password = et_putPW.getText().toString().trim();

        // 정보 저장
        LoginRequest loginRequest = new LoginRequest(username, password);

        retrofitClient = RetrofitClient.getInstance();
        serverAPI = RetrofitClient.getRetrofitInterface();

        serverAPI.Login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse result = response.body();

                    if(response.code() == 200) {
                        Toast.makeText(LoginActivity.this, username + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                        UserData.user_token = response.body().getToken();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    if(response.code() == 401) {
                        Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.\n(아이디 또는 비밀번호를 다시 확인해주세요)", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });


    }
}