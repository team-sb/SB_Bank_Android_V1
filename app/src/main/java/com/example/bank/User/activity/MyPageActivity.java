package com.example.bank.User.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Account.data.LoanListData;
import com.example.bank.Account.data.LoanListResponse;
import com.example.bank.Admin.data.UserListData;
import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.LoginActivity;
import com.example.bank.Account.activity.LoanListActivity;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.Main.activity.TransactionDetailsActivity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.User.data.UserDeleteResponse;
import com.example.bank.UserData;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {
    private static final String TAG = "MyPageActivity";

    private ImageButton myPage_ib_back;
    private ImageButton ib_settings;

    private TextView mypage_tv_accountNum;
    private TextView tv_seeLoan;
    private TextView mypage_tv_money;
    private TextView tv_transactions;
    private TextView tv_creditPrice;
    private TextView mypage_tv_name;

    private DrawerLayout drawerLayout;

    int sumLoanMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mypage_tv_name = (TextView) findViewById(R.id.mypage_tv_name);
        mypage_tv_name.setText("" + MainActivity.name);
        
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

                } else if(menuId == R.id.menu_delete) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                    builder.setTitle("정말로 계정을 탈퇴하시겠습니까?").setMessage("계속하시려면 \"Delete\"를 클릭해주세요.");

                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteAccount();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MyPageActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
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
            }
        });
    }

    private void deleteAccount() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.user_token;

        Call<UserDeleteResponse> call = serverAPI.deleteAccount(bearerUserToken, MainActivity.id);

        call.enqueue(new Callback<UserDeleteResponse>() {
            @Override
            public void onResponse(Call<UserDeleteResponse> call, Response<UserDeleteResponse> response) {
                if(response.code() == 200) {
                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }

            @Override
            public void onFailure(Call<UserDeleteResponse> call, Throwable t) {
            }
        });
    }
}