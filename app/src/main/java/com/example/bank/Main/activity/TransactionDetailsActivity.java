package com.example.bank.Main.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.Account.activity.SendDataActivity;
import com.example.bank.Account.data.LoanListData;
import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.Main.data.MainTranscationResponse;
import com.example.bank.R;
import com.example.bank.Main.adapter.TransactionDetailsAdapter;
import com.example.bank.Main.data.TransactionDetailsData;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionDetailsActivity extends AppCompatActivity {

    private static final String TAG = "TransactionDetailsActiv";

    private String transactionSelect;
    private TextView tv_transactionDetails;
    private ImageButton transactionDetails_ib_down;

    private ImageButton transactionDetails_ib_back;

    private LinearLayoutManager linearLayoutManager;

    private ArrayList<TransactionDetailsData> transactionList;

    private TransactionDetailsAdapter transactionDetailsAdapter;
    private RecyclerView recyclerView;

    static String selectedText = "전체";

    TextView transaction_tv_price;
    TextView transaction_tv_account;
    TextView transaction_tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        transaction_tv_send = (TextView) findViewById(R.id.transaction_tv_send);
        transaction_tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransactionDetailsActivity.this, SendDataActivity.class));
            }
        });

        transaction_tv_account = (TextView) findViewById(R.id.transaction_tv_account);
        transaction_tv_price = (TextView) findViewById(R.id.transaction_tv_price);

        if(!(MainActivity.myAccount.isEmpty())) {
            transaction_tv_account.setText(MainActivity.myAccount);
            transaction_tv_price.setText(MainActivity.price);
        }

        transactionDetails_ib_back = (ImageButton) findViewById(R.id.transactionDetails_ib_back);
        transactionDetails_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        transactionDetails_ib_down = (ImageButton) findViewById(R.id.transactionDetails_ib_down);
        transactionDetails_ib_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDialog();
            }
        });

        tv_transactionDetails = (TextView) findViewById(R.id.tv_transactionDetails);
        tv_transactionDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDialog();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_transactionDetails);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        transactionList = new ArrayList<>();

        transactionDetailsAdapter = new TransactionDetailsAdapter(transactionList, getApplicationContext());

        recyclerView.setAdapter(transactionDetailsAdapter);
    }

    private void checkDialog() {
        List<String> listItems = new ArrayList<>();
        listItems.add("전체");
        listItems.add("출금");
        listItems.add("입금");
        CharSequence[] items = listItems.toArray(new String[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionDetailsActivity.this);
        builder.setTitle("거래 내역 선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedText = items[which].toString();
                if(Objects.equals(selectedText, "전체")) {
                    detailAll();
                    tv_transactionDetails.setText("전체");
                } else if(Objects.equals(selectedText, "입금")) {
                    detailDeposit();
                    tv_transactionDetails.setText("입금");
                } else if(Objects.equals(selectedText, "출금")) {
                    detailWithDraw();
                    tv_transactionDetails.setText("출금");
                }
            }
        });
        builder.show();
    }

    private void detailAll() {

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.user_token;

        Call<MainTranscationResponse> call = serverAPI.transactionMain(bearerUserToken);

        call.enqueue(new Callback<MainTranscationResponse>() {
            @Override
            public void onResponse(Call<MainTranscationResponse> call, Response<MainTranscationResponse> response) {
                int result = response.code();

                if(result == 200) {
                    MainTranscationResponse mainTranscationResponse = response.body();
                    setTransaction(mainTranscationResponse);
                }
            }

            @Override
            public void onFailure(Call<MainTranscationResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }
    private void detailDeposit() {


        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.user_token;

        Call<MainTranscationResponse> call = serverAPI.transactionSendMain(bearerUserToken);

        call.enqueue(new Callback<MainTranscationResponse>() {
            @Override
            public void onResponse(Call<MainTranscationResponse> call, Response<MainTranscationResponse> response) {
                int result = response.code();

                if(result == 200) {
                    MainTranscationResponse mainTranscationResponse = response.body();
                    setTransaction(mainTranscationResponse);
                }
            }

            @Override
            public void onFailure(Call<MainTranscationResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }
    private void detailWithDraw() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.user_token;

        Call<MainTranscationResponse> call = serverAPI.transactionReceiveMain(bearerUserToken);

        call.enqueue(new Callback<MainTranscationResponse>() {
            @Override
            public void onResponse(Call<MainTranscationResponse> call, Response<MainTranscationResponse> response) {
                int result = response.code();

                if(result == 200) {
                    MainTranscationResponse mainTranscationResponse = response.body();
                    setTransaction(mainTranscationResponse);
                }
            }

            @Override
            public void onFailure(Call<MainTranscationResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    private void setTransaction(MainTranscationResponse mainTranscationResponse) {
        int trsactionSize = mainTranscationResponse.getTransactions().size();

        transactionList.clear();

        for(int i = 0; i < trsactionSize; i++) {
            JsonObject jsonObject = mainTranscationResponse.getTransactions().get(i);

            String setMemberId = jsonObject.get("memberId").toString();
            String setTargetAccount= jsonObject.get("targetAccount").toString();
            String setTargetName = jsonObject.get("targetName").toString();
            String setMoney = jsonObject.get("money").toString();
            String setTransactionDate = jsonObject.get("transactionDate").toString();
            String setTransactionType = jsonObject.get("transactionType").toString();
            String setBfBalance = jsonObject.get("bfBalance").toString();
            String setAftBalance= jsonObject.get("aftBalance").toString();


            String cutTransactionDate = setTransactionDate.substring(6,11);
            String cutTransactionTime = setTransactionDate.substring(15, 20);

            TransactionDetailsData transactionDetailsData = new TransactionDetailsData(cutTransactionDate, setTargetName, cutTransactionTime, setTransactionType, setAftBalance, setMoney);
            transactionList.add(transactionDetailsData);
            transactionDetailsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        detailAll();
        tv_transactionDetails.setText("전체");

    }
}