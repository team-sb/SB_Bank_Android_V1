package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;

public class ApprovalNumberActivity extends AppCompatActivity {

    ImageButton approvalNumber_ib_back;
    TextView approvalNumber_tv_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_number);

        approvalNumber_ib_back = (ImageButton) findViewById(R.id.approvalNumber_ib_back);
        approvalNumber_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        approvalNumber_tv_check = findViewById(R.id.approvalNumber_tv_check);
        approvalNumber_tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdraw();
            }
        });

    }

    private void withdraw() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        retrofit2.Call<Void> call = serverAPI.chargeAccount(UserData.temp_token, WithdrawActivity.withdrawMoney);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                int result = response.code();

                if(result == 201) { // 성공
                    UserData.temp_token = UserData.user_token;
                    Toast.makeText(ApprovalNumberActivity.this, WithdrawActivity.withdrawMoney + "원 출금", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (result == 403) { // 2차 인증
                    secCertified();
                } else if (result == 401) {
                    Toast.makeText(ApprovalNumberActivity.this, "2차 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {

            }
        });

    }

    private void secCertified() {
        startActivity(new Intent(ApprovalNumberActivity.this, SecPasswordActiviity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        withdraw();
    }
}