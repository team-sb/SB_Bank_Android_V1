package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
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

public class RegisterActivity extends AppCompatActivity {

    TextView tv_signIn;
    EditText et_RegisterName;
    EditText et_RegisterId;
    EditText et_RegisterPw;
    EditText et_RegisterSecPassword;

    ImageButton ib_Register;

    RetrofitClient retrofitClient;
    ServerAPI serverAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_RegisterName = (EditText) findViewById(R.id.et_RegisterName);
        et_RegisterId = (EditText) findViewById(R.id.et_RegisterId);
        et_RegisterPw = (EditText) findViewById(R.id.et_RegisterPw);
        et_RegisterSecPassword = (EditText) findViewById(R.id.et_RegistersecPassword);

        tv_signIn = (TextView) findViewById(R.id.tv_signIn);
        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ib_Register = (ImageButton) findViewById(R.id.ib_Register);
        ib_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    private void Register() {
        String username = et_RegisterId.getText().toString();
        String password = et_RegisterPw.getText().toString();
        String name = et_RegisterName.getText().toString();
        String secPassword = et_RegisterSecPassword.getText().toString();

        hideKeyboard();

        if(username.trim().length() == 0 || password.trim().length() == 0  || name.trim().length() == 0 || secPassword.trim().length() == 0 || username == null || password == null || name == null || secPassword == null) {
            Toast.makeText(RegisterActivity.this, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            RegisterResponse();
        }
    }

    private void hideKeyboard() // 키보드 숨기기
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_RegisterPw.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_RegisterName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_RegisterId.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_RegisterSecPassword.getWindowToken(), 0);
    }

    public void RegisterResponse() {
        String name = et_RegisterName.getText().toString().trim();
        String username = et_RegisterId.getText().toString().trim();
        String password = et_RegisterPw.getText().toString().trim();
        String sec_password = et_RegisterSecPassword.getText().toString().trim();

        // 정보 저장
        RegisterRequest requestRegister = new RegisterRequest(name, username, password, sec_password);

        retrofitClient = RetrofitClient.getInstance();
        serverAPI = RetrofitClient.getRetrofitInterface();

        serverAPI.Register(requestRegister).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    RegisterResponse result = response.body();

                    if (response.code() == 201) {
                        Toast.makeText(RegisterActivity.this, name + "님 가입을 환영합니다.\n로그인 후 서비스를 이용해주세요!", Toast.LENGTH_SHORT).show();

                        finish();
                    } else if (response.code() == 409) {
                        Toast.makeText(RegisterActivity.this, "닉네임 또는 아이디가 중복됩니다.\n변경 후 재시도 해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}