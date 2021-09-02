package com.example.bank.Auth.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Auth.data.LoginRequest;
import com.example.bank.Auth.data.LoginResponse;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.R;
import com.example.bank.RetrofitClient;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";

    private TextView tv_signUpButton;
    private ImageButton ib_next;


    private EditText et_putID;
    private EditText et_putPW;
    private RetrofitClient retrofitClient;
    private ServerAPI serverAPI;

    public static String username;
    public static String password;

    ProgressBar login_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_progressBar = (ProgressBar) findViewById(R.id.login_progressBar);

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
        login_progressBar.setVisibility(View.VISIBLE);

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
                    login_progressBar.setVisibility(View.GONE);

                    int resultCode = response.code();

                    if(resultCode == 200) {
                        Toast.makeText(LoginActivity.this, username + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                        UserData.user_token = response.body().getAccessToken();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else if(resultCode == 401) {
                        Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.\n비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else if(resultCode == 404) {
                        Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.\n아이디가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "예기치 못한 오류가 발생하였습니다.\n잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                login_progressBar.setVisibility(View.GONE);

                Toast.makeText(LoginActivity.this, "예기치 못한 오류가 발생하였습니다.\n잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}