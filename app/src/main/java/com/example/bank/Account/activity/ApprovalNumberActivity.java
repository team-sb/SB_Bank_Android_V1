package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;

public class ApprovalNumberActivity extends AppCompatActivity {

    private static final String TAG = "ApprovalNumberActivity";

    private ImageButton approvalNumber_ib_back;
    private TextView approvalNumber_tv_check;
    private TextView tv_approvalNumber;

    private TextView tv_reissuance;

    int minute = 30;
    int second = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_number);

        tv_reissuance = (TextView) findViewById(R.id.tv_reissuance);
        tv_reissuance.setVisibility(View.GONE);

        tv_reissuance.setOnClickListener(new View.OnClickListener() { // 새로고침
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = getIntent();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        startTimer();

        Random random = new Random();

        tv_approvalNumber = (TextView) findViewById(R.id.tv_approvalNumber);
        int approvalNumber = random.nextInt(999999);
        tv_approvalNumber.setText("" + approvalNumber);

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
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    private void startTimer() {
        TextView tv_second = (TextView) findViewById(R.id.tv_second);

        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_second.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tv_second.setText("00");

                tv_reissuance.setVisibility(View.VISIBLE);
            }

        }.start();
    }
}