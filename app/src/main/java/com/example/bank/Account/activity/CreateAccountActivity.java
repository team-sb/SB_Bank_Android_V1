package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Account.data.AccountResponse;
import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

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

//        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);
//
//        String bearerUserToken = "Bearer " + UserData.temp_token;
//
//        Call<AccountResponse> call = serverAPI.createAccount(bearerUserToken);
//
//        call.enqueue(new Callback<AccountResponse>() {
//            @Override
//            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
//                int result = response.code();
//
//                if(result == 201) { // 성공
//                    Toast.makeText(CreateAccountActivity.this, "계좌 생성이 완료되었습니다!", Toast.LENGTH_SHORT).show();
//                    UserData.temp_token = UserData.user_token;
//                    tv_newAccount.setText(response.body().getAccount());
//
//                    accountExist = true;
//
//                } else { // 2차 인증
//                    Toast.makeText(CreateAccountActivity.this, "ERROR : 페이지를 새로고침 해주세요", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AccountResponse> call, Throwable t) {
//
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        tv_newAccount.setText(MainActivity.accountNum);
    }
}