package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bank.Auth.activity.LoginActivity;
import com.example.bank.Main.activity.MainActivity;

public class StartActivity extends AppCompatActivity {

    private ImageButton ib_startBanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ib_startBanking = (ImageButton) findViewById(R.id.ib_startBanking);

        ib_startBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });
    }
}