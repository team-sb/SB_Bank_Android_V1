package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.R;
import com.example.bank.UserData;

import java.util.Objects;

public class SendDataActivity extends AppCompatActivity {

    ImageButton sendData_ib_back;

    TextView tv_finish;

    EditText et_accountNum;

    public static String accountNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        UserData.temp_token = UserData.user_token;

        et_accountNum = (EditText) findViewById(R.id.et_accountNum);

        sendData_ib_back = (ImageButton) findViewById(R.id.sendData_ib_back);
        sendData_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountNum = et_accountNum.getText().toString();
                if(!Objects.equals(accountNum, "")) {
                    startActivity(new Intent(SendDataActivity.this, SendActivity.class));
                }
            }
        });

    }

/*    private void depositSend() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        Call<Void> call = serverAPI.transferAccount(UserData.temp_token, accountNum);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int result = response.code();

                if(result == 201) { // 성공
                    UserData.temp_token = UserData.user_token;
                } else if (result == 403) { // 2차 인증
                    secCertified();
                } else if (result == 401) {
                    Toast.makeText(SendDataActivity.this, "Token이 유효하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void secCertified() {
        startActivity(new Intent(SendDataActivity.this, SecPasswordActiviity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        depositSend();
    }*/
}