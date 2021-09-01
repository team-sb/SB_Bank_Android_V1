package com.example.bank.User.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.Account.activity.DepositActivity;
import com.example.bank.Account.activity.SendDataActivity;
import com.example.bank.Account.activity.WithdrawActivity;
import com.example.bank.Auth.activity.LoginActivity;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.R;
import com.example.bank.UserData;
import com.google.android.material.navigation.NavigationView;

public class MyPageActivity extends AppCompatActivity {

    ImageButton myPage_ib_back;
    ImageButton ib_settings;

    TextView mypage_tv_accountNum;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

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
    }
}