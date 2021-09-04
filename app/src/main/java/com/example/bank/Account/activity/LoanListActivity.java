package com.example.bank.Account.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.bank.ApiProvider;
import com.example.bank.Account.adapter.LoanListAdapter;
import com.example.bank.Account.data.LoanListData;
import com.example.bank.Account.data.LoanListResponse;
import com.example.bank.Auth.activity.LoginActivity;
import com.example.bank.Main.activity.MainActivity;
import com.example.bank.R;
import com.example.bank.ServerAPI;
import com.example.bank.User.activity.MyPageActivity;
import com.example.bank.UserData;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanListActivity extends AppCompatActivity {

    private static final String TAG = "LoanListActivity";

    private ArrayList<LoanListData> arraylist;
    private LinearLayout linearLayout;
    private LinearLayoutManager linearLayoutManager;
    private LoanListAdapter loanListAdapter;

    private ImageButton loanList_ib_back;

    private RecyclerView rv_loan;

    private LoanListResponse loanListResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);

        loanList_ib_back = (ImageButton) findViewById(R.id.loanList_ib_back);
        loanList_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv_loan = (RecyclerView) findViewById(R.id.rv_loan);

        linearLayoutManager = new LinearLayoutManager(this);
        rv_loan.setLayoutManager(linearLayoutManager);

        arraylist = new ArrayList<>();

        loanListAdapter = new LoanListAdapter(arraylist, getApplicationContext());

        rv_loan.setAdapter(loanListAdapter);
    }

    protected void onResume() {
        super.onResume();

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearUserToken = "Bearer " + UserData.user_token;

        Call<LoanListResponse> call = serverAPI.loanUser(bearUserToken);

        call.enqueue(new Callback<LoanListResponse>() {
            @Override
            public void onResponse(Call<LoanListResponse> call, Response<LoanListResponse> response) {
                Log.d(TAG, "onResponse11: " + response.code());
                int result = response.code();

                if(result == 200) {
                    loanListResponse = response.body();
                    setLoanList(loanListResponse);
                }
            }

            @Override
            public void onFailure(Call<LoanListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void setLoanList(LoanListResponse loanList) {
        int loanSize = loanList.getLoans().size();

        for(int i = 0; i < loanSize; i++) {
            JsonObject jsonObject = loanList.getLoans().get(i);

            Log.d(TAG, "setLoanList: " + jsonObject.get("money").toString());
            String setMoney = jsonObject.get("money").toString();
            String setInterest = jsonObject.get("interest").toString();
            String setBDate = jsonObject.get("borrowedDate").toString();
            String setEDate = jsonObject.get("expirationDate").toString();

            Log.d(TAG, "setLoanList: jsonobject" + jsonObject);

            String cutBDate = setBDate.substring(1, 11);
            String cutEDate = setEDate.substring(1, 11);

            LoanListData loanListData = new LoanListData(setMoney, setInterest, cutBDate, cutEDate);
            arraylist.add(loanListData);

            loanListAdapter.notifyDataSetChanged();
        }
    }
}