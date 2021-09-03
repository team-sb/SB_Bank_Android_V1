package com.example.bank.User.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.Account.data.LoanListData;
import com.example.bank.Account.data.LoanListResponse;
import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.LoginActivity;
import com.example.bank.Account.activity.LoanListActivity;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.Main.activity.TransactionDetailsActivity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {
    private static final String TAG = "MyPageActivity";

    ImageButton myPage_ib_back;
    ImageButton ib_settings;

    TextView mypage_tv_accountNum;
    TextView tv_seeLoan;
    TextView mypage_tv_money;
    TextView tv_transactions;
    TextView tv_creditPrice;

    DrawerLayout drawerLayout;

    int sumLoanMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        if(!(MainActivity.myAccount.isEmpty())) {
            mypage_tv_money = (TextView) findViewById(R.id.mypage_tv_money);
            mypage_tv_money.setText(MainActivity.price);
        }

        tv_transactions = (TextView) findViewById(R.id.tv_transactions);
        tv_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPageActivity.this, TransactionDetailsActivity.class));
            }
        });

        tv_seeLoan = (TextView) findViewById(R.id.tv_seeLoan);
        tv_seeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPageActivity.this, LoanListActivity.class));
            }
        });

        mypage_tv_accountNum = (TextView) findViewById(R.id.mypage_tv_accountNum);
        mypage_tv_accountNum.setText(MainActivity.myAccount);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_settings);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_settings_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(Gravity.RIGHT);

                int menuId = item.getItemId();

                if(menuId == R.id.menu_logout) {

                    Intent intent = new Intent(MyPageActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                }

                return true;
            }
        });

        ib_settings = (ImageButton) findViewById(R.id.ib_settings);
        ib_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });

        myPage_ib_back = (ImageButton) findViewById(R.id.myPage_ib_back);
        myPage_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_creditPrice = (TextView) findViewById(R.id.tv_creditPrice);

        getLoanPrice();
    }

    private void getLoanPrice() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearUserToken = "Bearer " + UserData.user_token;

        Call<LoanListResponse> call = serverAPI.loanUser(bearUserToken);

        call.enqueue(new Callback<LoanListResponse>() {
            @Override
            public void onResponse(Call<LoanListResponse> call, Response<LoanListResponse> response) {
                Log.d(TAG, "onResponse11: " + response.code());
                int result = response.code();

                if(result == 200) {
                    LoanListResponse loanList = response.body();

                    int loanSize = loanList.getLoans().size();

                    for(int i = 0; i < loanSize; i++) {
                        JsonObject jsonObject = loanList.getLoans().get(i);
                        String loanMoney = jsonObject.get("money").toString();

                        sumLoanMoney += Integer.parseInt(loanMoney);
                    }

                    tv_creditPrice.setText("" + sumLoanMoney);
                }
            }

            @Override
            public void onFailure(Call<LoanListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}