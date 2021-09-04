package com.example.bank.Admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.sip.SipErrorCode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bank.Account.adapter.LoanListAdapter;
import com.example.bank.Account.data.LoanListData;
import com.example.bank.Account.data.LoanListResponse;
import com.example.bank.Admin.adapter.UserListAdapter;
import com.example.bank.Admin.data.UserListData;
import com.example.bank.Admin.data.UserListResponse;
import com.example.bank.ApiProvider;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    private static final String TAG = "AdminActivity";

    private ImageButton admin_ib_Back;

    private ArrayList<UserListData> arraylist;
    private LinearLayout linearLayout;
    private LinearLayoutManager linearLayoutManager;

    private UserListAdapter userListAdapter;

    private RecyclerView rv_admin;

    private UserListResponse userListResponse;

    private String userId;
    private String name;
    private String userName;
    private String userAuthority;

    TextView tv_userMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tv_userMode = (TextView) findViewById(R.id.tv_userMode);
        tv_userMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });

        admin_ib_Back = (ImageButton) findViewById(R.id.admin_ib_Back);
        admin_ib_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv_admin = (RecyclerView) findViewById(R.id.rv_admin);

        linearLayoutManager = new LinearLayoutManager(this);
        rv_admin.setLayoutManager(linearLayoutManager);

        arraylist = new ArrayList<>();

        userListAdapter = new UserListAdapter(arraylist, getApplicationContext());

        rv_admin.setAdapter(userListAdapter);

        getUserList();
    }

    private void getUserList() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearUserToken = "Bearer " + UserData.user_token;

        Call<UserListResponse> call = serverAPI.userList(bearUserToken);

        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                setUserList(response.body());
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void setUserList(UserListResponse userList) {
        int listSize = userList.getUsersList().size();
        for(int i = 0; i < listSize; i++) {
            JsonObject jsonObject = userList.getUsersList().get(i);

            userId = jsonObject.get("id").toString().replace("\"", "");
            name = jsonObject.get("name").toString().replace("\"", "");
            userName = jsonObject.get("userName").toString().replace("\"", "");
            userAuthority = jsonObject.get("authority").toString().replace("\"", "");

            UserListData userListData = new UserListData(userId, name, userName, userAuthority);
            arraylist.add(userListData);
            userListAdapter.notifyDataSetChanged();
        }
    }
}