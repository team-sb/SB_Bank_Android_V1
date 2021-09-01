package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Account.data.AccountLoanResponse;
import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanActivity extends AppCompatActivity {
    
    private static final String TAG = "LoanActivity";
    
    ImageButton loan_ib_back;

    EditText et_loanPrice;

    TextView tv_applicationLoan;

    String stLoanPrice;

    String loanMoney;
    String loanInterest;
    String loanBorrowedDate;
    String loanLoanExpirationDate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        
        loan_ib_back = (ImageButton) findViewById(R.id.loan_ib_back);
        loan_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_loanPrice = (EditText) findViewById(R.id.et_loanPrice);

        tv_applicationLoan = (TextView) findViewById(R.id.tv_applicationLoan);
        tv_applicationLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stLoanPrice = et_loanPrice.getText().toString();
                if(stLoanPrice.isEmpty()) {
                    Toast.makeText(LoanActivity.this, "대출할 금액을 입력해주세요.", Toast.LENGTH_SHORT).show();

                    return;
                }

                startActivity(new Intent(LoanActivity.this, SecPasswordActiviity.class));
            }
        });
    }

    protected void onResume() {
        super.onResume();

        if (SecPasswordActiviity.secSuccess) {
            ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

            Log.d(TAG, "user token" + UserData.user_token + "sec token" + UserData.sec_token + "temp token" + UserData.temp_token);

            String bearerUserToken = "Bearer " + UserData.temp_token;

            serverAPI.loanAccount()
//            Call<AccountLoanResponse> call = serverAPI.loanAccount(bearerUserToken, "100000");
//
//            call.enqueue(new Callback<AccountLoanResponse>() {
//                @Override
//                public void onResponse(Call<AccountLoanResponse> call, Response<AccountLoanResponse> response) {
//                    Log.d(TAG, "user token" + UserData.user_token + "sec token" + UserData.sec_token + "temp token" + UserData.temp_token);
//                    Log.d(TAG, "111onResponse: " + response.code());
//                }
//
//                @Override
//                public void onFailure(Call<AccountLoanResponse> call, Throwable t) {
//                    Log.d(TAG, "111onFailure: ");
//                }
//            });
            
        }
    }
}