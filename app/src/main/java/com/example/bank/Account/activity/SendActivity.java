package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendActivity extends AppCompatActivity {

    ImageButton send_ib_back;

    ImageButton send_ib_sendMoney;

    EditText send_et_money;

    String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        UserData.temp_token = UserData.user_token;

        send_et_money = (EditText) findViewById(R.id.send_et_money);

        send_ib_back = (ImageButton) findViewById(R.id.send_ib_back);
        send_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send_ib_sendMoney = (ImageButton) findViewById(R.id.send_ib_sendMoney);
        send_ib_sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = send_et_money.getText().toString();
                if(!(Objects.equals(money, "") || money == null)) {
                    sendMoney();
                }
            }
        });
    }

    private void sendMoney() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        Call<Void> call = serverAPI.transferAccount(UserData.temp_token, money, SendDataActivity.accountNum);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int result = response.code();

                if(result == 201) { // 성공
                    UserData.temp_token = UserData.user_token;
                } else if (result == 403) { // 2차 인증
                    secCertified();
                } else if (result == 401) {
                    Toast.makeText(SendActivity.this, "2차 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void secCertified() {
        startActivity(new Intent(SendActivity.this, SecPasswordActiviity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        sendMoney();
    }
}