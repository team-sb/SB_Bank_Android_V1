package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class SendDataActivity extends AppCompatActivity {

    ImageButton sendData_ib_back;

    TextView tv_finish;

    EditText et_accountNum;

    String accountNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

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
                if(!(accountNum == null || Objects.equals(accountNum, ""))) {
                    depositSend();
                }
            }
        });

    }

    private void depositSend() {
    }
}