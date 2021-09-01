package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bank.Account.activity.LoanActivity;

public class LoanCheckActivity extends AppCompatActivity {

    TextView loan_tv_finish;
    TextView tv_loanMoney;
    TextView tv_interest;
    TextView tv_borrowedDate;
    TextView tv_loanExpirationDate;

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
        tv_borrowedDate.setText(LoanActivity.loanBorrowedDate);
        tv_loanExpirationDate.setText(LoanActivity.loanLoanExpirationDate);

        loan_tv_finish = (TextView) findViewById(R.id.loan_tv_finish);
        loan_tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}