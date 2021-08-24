package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class SendActivity extends AppCompatActivity {

    ImageButton send_ib_back;

    ImageButton send_ib_sendMoney;

    EditText send_et_money;

    String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

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
    }
}