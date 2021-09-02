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

import com.example.bank.ApiProvider;
import com.example.bank.Auth.activity.SecPasswordActiviity;
import com.example.bank.Main.data.MainTranscationResponse;
import com.example.bank.R;
import com.example.bank.Main.adapter.TransactionDetailsAdapter;
import com.example.bank.Main.data.TransactionDetailsData;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

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
                    startActivity(new Intent(TransactionDetailsActivity.this, SecPasswordActiviity.class));
                } else if(Objects.equals(selectedText, "입금")) {
                    startActivity(new Intent(TransactionDetailsActivity.this, SecPasswordActiviity.class));
                } else if(Objects.equals(selectedText, "출금")) {
                    startActivity(new Intent(TransactionDetailsActivity.this, SecPasswordActiviity.class));
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
                Log.d(TAG, "onResponse: 성공");
            }

            @Override
            public void onFailure(Call<MainTranscationResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });



        transactionList.clear();
        TransactionDetailsData transactionDetailsData = new TransactionDetailsData("8-25", "임세현", "8:40",
                "send", "-10", "200,000");
        transactionList.add(transactionDetailsData);
        transactionDetailsAdapter.notifyDataSetChanged();
    }
    private void detailDeposit() {

    }
    private void detailWithDraw() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(SecPasswordActiviity.secSuccess) {
            if (Objects.equals(selectedText, "전체")) {
                detailAll();
            } else if (Objects.equals(selectedText, "입금")) {
                detailDeposit();
            } else if (Objects.equals(selectedText, "출금")) {
                detailWithDraw();
            }
        }
    }
}