package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bank.Account.activity.LoanActivity;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.R;

public class LoanCheckActivity extends AppCompatActivity {

    private TextView loan_tv_finish;
    private TextView tv_loanMoney;
    private TextView tv_interest;
    private TextView tv_borrowedDate;
    private TextView tv_loanExpirationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_check);

        tv_loanMoney = (TextView) findViewById(R.id.tv_loanMoney);
        tv_interest = (TextView) findViewById(R.id.tv_interest);
        tv_borrowedDate = (TextView) findViewById(R.id.tv_borrowedDate);
        tv_loanExpirationDate = (TextView) findViewById(R.id.tv_loanExpirationDate);

        tv_loanMoney.setText(LoanActivity.loanMoney);
        tv_interest.setText(LoanActivity.loanInterest);

        String borrowedDate = LoanActivity.loanBorrowedDate.substring(0, 10);
        String loanExpirationDate = LoanActivity.loanLoanExpirationDate.substring(0, 10);

        tv_borrowedDate.setText(borrowedDate);
        tv_loanExpirationDate.setText(loanExpirationDate);

        loan_tv_finish = (TextView) findViewById(R.id.loan_tv_finish);
        loan_tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanCheckActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}