package com.example.bank.Account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.R;

public class WithdrawActivity extends AppCompatActivity {

    ImageButton withDraw_ib_back;

    TextView tv_createApoorovalNum;
    TextView withdraw_tv_cancel;

    EditText withdraw_et_money;

    public static int withdrawMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        withdraw_et_money = (EditText) findViewById(R.id.withdraw_et_money);

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
                withdrawMoney = Integer.parseInt(withdraw_et_money.getText().toString()) * -1;
                if(!(withdrawMoney == 0)) {
                    startActivity(new Intent(WithdrawActivity.this, ApprovalNumberActivity.class));
                }
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