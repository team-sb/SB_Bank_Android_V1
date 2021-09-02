package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    public static String loanMoney;
    public static String loanInterest;
    public static String loanBorrowedDate;
    public static String loanLoanExpirationDate;
    
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

            Log.d(TAG, "user token\n" + UserData.user_token + "sec token\n" + UserData.sec_token + "temp token\n" + UserData.temp_token);

            String bearerUserToken = "Bearer " + UserData.temp_token;

            serverAPI.accountLoan(bearerUserToken, stLoanPrice).enqueue(new Callback<AccountLoanResponse>() {
                @Override
                public void onResponse(Call<AccountLoanResponse> call, Response<AccountLoanResponse> response) {
                    int result = response.code();

                    if(result == 200) {
                        UserData.temp_token = UserData.user_token;
                        SecPasswordActiviity.secSuccess = false;

                        loanMoney = response.body().getMoney().toString();
                        loanInterest = Double.toString(response.body().getInterest());
                        loanBorrowedDate = response.body().getBorrowedDate();
                        loanLoanExpirationDate = response.body().getLoanExpirationDate();

                        startActivity(new Intent(LoanActivity.this, LoanCheckActivity.class));
                    } else if (result == 403 || result == 401) { // 2차 인증
                        Toast.makeText(LoanActivity.this, "2차 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else if (result == 404) {
                        Toast.makeText(LoanActivity.this, "계좌가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AccountLoanResponse> call, Throwable t) {
                }
            });

        }
    }
}