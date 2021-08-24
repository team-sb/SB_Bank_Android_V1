package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DepositActivity extends AppCompatActivity {

    ImageButton deposit_ib_back;

    TextView tv_deposit;
    TextView tv_depositCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

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
                deposit();
            }
        });

        tv_depositCancel = (TextView) findViewById(R.id.tv_depositCancel);
        tv_depositCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositCancel();
            }
        });
    }

    private void deposit() {

    }
    private void depositCancel() {

    }
}