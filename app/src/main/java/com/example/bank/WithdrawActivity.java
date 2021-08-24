package com.example.bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WithdrawActivity extends AppCompatActivity {

    ImageButton withDraw_ib_back;

    TextView tv_createApoorovalNum;
    TextView withdraw_tv_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        withDraw_ib_back = (ImageButton) findViewById(R.id.withDraw_ib_back);
        withDraw_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_createApoorovalNum = (TextView) findViewById(R.id.tv_createApoorovalNum);
        tv_createApoorovalNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WithdrawActivity.this, ApprovalNumberActivity.class));
            }
        });

        withdraw_tv_cancel = (TextView) findViewById(R.id.withdraw_tv_cancel);
        withdraw_tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}