package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bank.R;
import com.example.bank.UserData;

import java.util.Objects;

public class SendDataActivity extends AppCompatActivity {

    private ImageButton sendData_ib_back;

    private TextView tv_finish;
    private ImageView iv_finish;

    private EditText et_accountNum;

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
                if(!Objects.equals(accountNum, "0")) {
                    startActivity(new Intent(SendDataActivity.this, SendActivity.class));
                }
            }
        });

        iv_finish = (ImageView) findViewById(R.id.iv_finish);
        et_accountNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_finish.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.whiteText));
                iv_finish.setImageResource(R.drawable.pinkbox);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}