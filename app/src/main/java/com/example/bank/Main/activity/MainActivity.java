package com.example.bank.Main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Account.activity.CreateAccountActivity;
import com.example.bank.Account.activity.DepositActivity;
import com.example.bank.Account.data.AccountResponse;
import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.User.activity.MyPageActivity;
import com.example.bank.R;
import com.example.bank.Account.activity.SendDataActivity;
import com.example.bank.ServerAPI;
import com.example.bank.User.data.UserBalanceResponse;
import com.example.bank.UserData;
import com.example.bank.Account.activity.WithdrawActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView tv_price;
    TextView tv_makeAccount;
    TextView tv_qrCheck;
    TextView tv_name;

    ImageButton ib_sendMoney;
    ImageButton ib_menu;

    Button temp2;
    Button tempw;

    DrawerLayout drawerLayout;

    String tempActivityName;
    public static String myAccount;

    public static String accountNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib_sendMoney = (ImageButton) findViewById(R.id.ib_sendMoney);
        ib_sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SendDataActivity.class));
            }
        });

        // 임시 출금 이동
        tempw = (Button) findViewById(R.id.tempw);
        tempw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WithdrawActivity.class));
            }
        });

        // 임시 2차 비밀번호 이동
        temp2 = (Button) findViewById(R.id.temp2);
        temp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecPasswordActiviity.class));
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                int menuId = item.getItemId();

                if(menuId == R.id.menu_myPage) {
                    startActivity(new Intent(MainActivity.this, MyPageActivity.class));
                } else if(menuId == R.id.menu_send) {
                    startActivity(new Intent(MainActivity.this, DepositActivity.class));
                } else if(menuId == R.id.menu_sendAccount) {
                    startActivity(new Intent(MainActivity.this, SendDataActivity.class));
                } else if(menuId == R.id.menu_withDraw) {
                    startActivity(new Intent(MainActivity.this, WithdrawActivity.class));
                }

                return true;
            }
        });

        ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransactionDetailsActivity.class));
            }
        });

        tv_makeAccount = (TextView) findViewById(R.id.tv_makeAccount);
        tv_makeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("계좌를 생성하시겠습니까?").setMessage("계속하시려면 \"Create\"를 클릭해주세요.");

                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        makeAccount();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        tv_qrCheck = (TextView) findViewById(R.id.tv_qrCheck);
        tv_qrCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://nid.naver.com/login/qrCheckIn")));
            }
        });

        tv_name = (TextView) findViewById(R.id.tv_name);
    }

    private void makeAccount() {
        tempActivityName = "CreateAccountActivity";

        startActivity(new Intent(MainActivity.this, SecPasswordActiviity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

         getBalance();

        if(SecPasswordActiviity.secSuccess) { // 계좌 생성

            ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

            String bearerUserToken = "Bearer " + UserData.temp_token;

            Call<AccountResponse> call = serverAPI.createAccount(bearerUserToken);

            call.enqueue(new Callback<AccountResponse>() {
                @Override
                public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                    int result = response.code();

                    if(result == 201) { // 성공
                        Toast.makeText(MainActivity.this, "계좌 생성이 완료되었습니다!", Toast.LENGTH_SHORT).show();

                        UserData.temp_token = UserData.user_token;
                        SecPasswordActiviity.secSuccess = false;

                        accountNum = response.body().getAccount();

                        startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));

                    } else if (result == 409) {
                        Toast.makeText(MainActivity.this, "계좌가 이미 있습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AccountResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void getBalance() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.user_token;

        Call<UserBalanceResponse> call = serverAPI.balanceMain(bearerUserToken);

        call.enqueue(new Callback<UserBalanceResponse>() {
            @Override
            public void onResponse(Call<UserBalanceResponse> call, Response<UserBalanceResponse> response) {
                if (response.code() == 200) {
                    tv_price.setText(response.body().getBalance());
                    tv_name.setText(response.body().getName());
                    myAccount = response.body().getAccountNumber();
                }
                if (response.code() == 404) {
                    Toast.makeText(MainActivity.this, "계좌가 존재하지 않습니다.\n우측 상단 \"계좌 생성\" 버튼을 클릭하여 계좌를 생성해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserBalanceResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}