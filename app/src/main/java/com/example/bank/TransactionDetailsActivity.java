package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class TransactionDetailsActivity extends AppCompatActivity {

    Spinner sp_transactionDetails;
    String transactionSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        sp_transactionDetails = (Spinner) findViewById(R.id.sp_transactionDetails);

        sp_transactionDetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)parent.getChildAt(position)).setTextColor(Color.WHITE);

                transactionSelect = parent.getItemAtPosition(position).toString();
                if(Objects.equals(transactionSelect, "전체")) {
                    detailAll();
                } else if(Objects.equals(transactionSelect, "입금")) {
                    detailSend();
                } else if(Objects.equals(transactionSelect, "출금")) {
                    detailReceive();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void detailAll() {

    }
    private void detailSend() {

    }
    private void detailReceive() {

    }
}