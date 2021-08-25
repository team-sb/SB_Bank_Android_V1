package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    ImageButton createAccount_ib_back;
    TextView tv_check;
    TextView tv_newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        UserData.temp_token = UserData.user_token; // Temp

        tv_newAccount = (TextView) findViewById(R.id.tv_newAccount);

        createAccount_ib_back = (ImageButton) findViewById(R.id.createAccount_ib_back);
        createAccount_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();
            }
        });
    }

    private void createAccount() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        Call<AccountResponse> call = serverAPI.createAccount(UserData.temp_token);

        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                int result = response.code();

                if(result == 201) { // 성공
                    UserData.temp_token = UserData.user_token;
                    tv_newAccount.setText(response.body().getAccount());
                } else if (result == 403) { // 2차 인증
                    secCertified();
                } else if (result == 401) {
                    Toast.makeText(CreateAccountActivity.this, "Token이 유효하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

            }
        });

    }

    private void secCertified() {
        startActivity(new Intent(CreateAccountActivity.this, SecPasswordActiviity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        createAccount();
    }
}