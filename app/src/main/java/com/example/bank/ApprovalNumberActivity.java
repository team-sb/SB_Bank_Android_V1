package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
                finish();
            }
        });
    }
}