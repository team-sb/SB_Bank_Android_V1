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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionDetailsActivity extends AppCompatActivity {

    private static final String TAG = "TransactionDetailsActiv";

    String transactionSelect;
    TextView tv_transactionDetails;
    ImageButton transactionDetails_ib_down;

    ImageButton transactionDetails_ib_back;

    LinearLayoutManager linearLayoutManager;

    ArrayList<TransactionDetailsData> transactionList;

    TransactionDetailsAdapter transactionDetailsAdapter;
    RecyclerView recyclerView;

    static String selectedText = "전체";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

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
                } else if(Objects.equals(selectedText, "입금")) {
                    detailDeposit();
                } else if(Objects.equals(selectedText, "출금")) {
                    detailWithDraw();
                }
            }
        });
        builder.show();
    }

    private void detailAll() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.temp_token;

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

    }
    private void detailWithDraw() {

    }

    private void setTransaction(MainTranscationResponse mainTranscationResponse) {
        int trsactionSize = mainTranscationResponse.getTransactions().size();
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

            String transactionMoney = Integer.toString(Integer.parseInt(setBfBalance) + Integer.parseInt(setAftBalance));

            TransactionDetailsData transactionDetailsData = new TransactionDetailsData(setTransactionDate, setTargetName, setTransactionDate, setTransactionType,  transactionMoney, setMoney);
            transactionList.add(transactionDetailsData);
            transactionDetailsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Objects.equals(selectedText, "전체")) {
            detailAll();
        } else if (Objects.equals(selectedText, "입금")) {
            detailDeposit();
        } else if (Objects.equals(selectedText, "출금")) {
            detailWithDraw();
        }

    }
}