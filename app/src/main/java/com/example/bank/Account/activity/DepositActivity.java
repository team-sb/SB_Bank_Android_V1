package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import retrofit2.Callback;
import retrofit2.Response;

public class DepositActivity extends AppCompatActivity {

    ImageButton deposit_ib_back;

    TextView tv_deposit;
    TextView tv_depositCancel;

    EditText deposit_et_money;

    public static int depositMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        deposit_et_money = (EditText) findViewById(R.id.deposit_et_money);

        deposit_ib_back = (ImageButton) findViewById(R.id.deposit_ib_back);
        deposit_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_deposit = (TextView) findViewById(R.id.tv_deposit);
        tv_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositMoney = Integer.parseInt(deposit_et_money.getText().toString());
                if(!(depositMoney == 0)) {
                    deposit();
                }
            }
        });

        tv_depositCancel = (TextView) findViewById(R.id.tv_depositCancel);
        tv_depositCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void deposit() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        retrofit2.Call<Void> call = serverAPI.chargeAccount(UserData.temp_token, depositMoney);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                int result = response.code();

                if(result == 201) { // 성공
                    UserData.temp_token = UserData.user_token;
                    Toast.makeText(DepositActivity.this, depositMoney + "원 입금", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (result == 403) { // 2차 인증
                    secCertified();
                } else if (result == 401) {
                    Toast.makeText(DepositActivity.this, "2차 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {

            }
        });

    }

    private void secCertified() {
        startActivity(new Intent(DepositActivity.this, SecPasswordActiviity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        deposit();
    }
}