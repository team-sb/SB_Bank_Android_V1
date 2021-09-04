package com.example.bank.Admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.Admin.adapter.UserListAdapter;
import com.example.bank.Admin.data.UserInfoResponse;
import com.example.bank.ApiProvider;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminUserInfoActivity extends AppCompatActivity {

    private static final String TAG = "AdminUserInfoActivity";

    private ImageButton userInfo_ib_back;

    private TextView tv_userInfoName;
    private TextView tv_userInfoUserName;
    private TextView tv_userInfoId;
    private TextView tv_userInfoAuthority;
    private TextView tv_userInfoAccount;
    private TextView tv_userInfoBalance;

    private EditText et_editMoney;

    private TextView tv_editMoney;

    private int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_info);

        userInfo_ib_back = (ImageButton) findViewById(R.id.userInfo_ib_back);
        tv_userInfoName = (TextView) findViewById(R.id.tv_userInfoName);
        tv_userInfoUserName = (TextView) findViewById(R.id.tv_userInfoUserName);
        tv_userInfoId = (TextView) findViewById(R.id.tv_userInfoId);
        tv_userInfoAuthority = (TextView) findViewById(R.id.tv_userInfoAuthority);
        tv_userInfoAccount = (TextView) findViewById(R.id.tv_userInfoAccount);
        tv_userInfoBalance = (TextView) findViewById(R.id.tv_userInfoBalance);

        tv_userInfoId.setText("유저 고유 값 : " + UserListAdapter.userId);
        tv_userInfoName.setText(UserListAdapter.name + "님");
        tv_userInfoUserName.setText("ID : " + UserListAdapter.userName);
        tv_userInfoAuthority.setText(UserListAdapter.userAuthority);

        EditText et_editMoney = (EditText) findViewById(R.id.et_editMoney);

        tv_editMoney = (TextView) findViewById(R.id.tv_editBalance);

        tv_editMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = Integer.parseInt(et_editMoney.getText().toString());
                if(money == 0) {
                    Toast.makeText(AdminUserInfoActivity.this, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    editMoney();
                }
            }
        });

        userInfo_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getUserInfo();
    }

    private void editMoney() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearUserToken = "Bearer " + UserData.user_token;

        Call<Void> call = serverAPI.editBalance(bearUserToken, UserListAdapter.userId, money);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int result = response.code();
                if(result == 200) {
                    Toast.makeText(AdminUserInfoActivity.this, "성공적으로" + UserListAdapter.name + "님에게" + money + "원을 입금 하였습니다.", Toast.LENGTH_SHORT).show();
                } else if (result == 404) {
                    Toast.makeText(AdminUserInfoActivity.this, "계좌가 없는 유저입니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void getUserInfo() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearUserToken = "Bearer " + UserData.user_token;

        Call<UserInfoResponse> call = serverAPI.userInfo(bearUserToken, UserListAdapter.userId);

        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                int result = response.code();

                if(result == 200) {
                    tv_userInfoAccount.setText("Account : " + response.body().getAccountNumber());
                    tv_userInfoBalance.setText("Balance : " + response.body().getBalance());
                } else if (result == 404) {
                    Toast.makeText(AdminUserInfoActivity.this, "해당 유저가 계좌를 개설하지 않아 조회에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
            }
        });
    }
}