package com.example.bank.User.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bank.R;

public class MyPageActivity extends AppCompatActivity {

    ImageButton myPage_ib_back;
    ImageButton ib_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ib_settings = (ImageButton) findViewById(R.id.ib_settings);
        myPage_ib_back = (ImageButton) findViewById(R.id.myPage_ib_back);
        myPage_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}