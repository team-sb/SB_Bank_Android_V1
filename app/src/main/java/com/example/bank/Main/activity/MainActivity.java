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
        if(CreateAccountActivity.accountExist) {
            Toast.makeText(MainActivity.this, "계좌가 이미 존재합니다.", Toast.LENGTH_SHORT).show();

            return;
        }

        if(Objects.equals(tempActivityName, "CreateAccountActivity")) {
            startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
        }
        getBalance(); // 기본 정보 세팅


    }

    private void getBalance() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        Call<UserBalanceResponse> call = serverAPI.balanceMain(UserData.user_token);

        call.enqueue(new Callback<UserBalanceResponse>() {
            @Override
            public void onResponse(Call<UserBalanceResponse> call, Response<UserBalanceResponse> response) {
                if(response.code() == 200) {
                    tv_price.setText(response.body().getBalance());
                    tv_name.setText(response.body().getName());
                }
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<UserBalanceResponse> call, Throwable t) {

            }
        });
    }
}