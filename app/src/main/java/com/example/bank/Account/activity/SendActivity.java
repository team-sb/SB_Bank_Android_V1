package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Account.data.SendRequest;
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
    private static final String TAG = "SendActivity";

    private ImageButton send_ib_back;

    private ImageButton send_ib_sendMoney;

    private EditText send_et_money;

    private String stMoney;
    int money;

    private TextView tv_sendAccountNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        UserData.temp_token = UserData.user_token;

        tv_sendAccountNum = (TextView) findViewById(R.id.tv_sendAccountNum);
        tv_sendAccountNum.setText(SendDataActivity.accountNum);

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
                stMoney = send_et_money.getText().toString();
                money = Integer.parseInt(stMoney);
                if(money != 0 || !(Integer.toString(money).equals(""))) {
                    startActivity(new Intent(SendActivity.this, SecPasswordActiviity.class));
                } else {
                    Toast.makeText(SendActivity.this, "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(SecPasswordActiviity.secSuccess) {
            ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

            String bearerUserToken = "Bearer " + UserData.temp_token;

            SendRequest sendRequest = new SendRequest(Integer.parseInt(SendDataActivity.accountNum), money * -1);

            serverAPI.transferAccount(bearerUserToken, sendRequest).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    int result = response.code();

                    if(result == 200) { // 성공
                        UserData.temp_token = UserData.user_token;
                        SecPasswordActiviity.secSuccess = false;

                        Toast.makeText(SendActivity.this, "send account : " + SendDataActivity.accountNum + "\nsend money : " + money, Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (result == 403) { // 2차 인증
                        Toast.makeText(SendActivity.this, "2차 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else if (result == 401) {
                        Toast.makeText(SendActivity.this, "2차 비밀번호가 일치하지 않습니다..", Toast.LENGTH_SHORT).show();
                    } else if (result == 404) {
                        Toast.makeText(SendActivity.this, "계좌 번호가 존재하지 않거나 잔액이 부족합니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });
        }
    }
}