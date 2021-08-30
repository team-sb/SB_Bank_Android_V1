package com.example.bank.Main.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.R;
import com.example.bank.Main.adapter.TransactionDetailsAdapter;
import com.example.bank.Main.data.TransactionDetailsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionDetailsActivity extends AppCompatActivity {

    String transactionSelect;
    TextView tv_transactionDetails;
    ImageButton transactionDetails_ib_down;

    ImageButton transactionDetails_ib_back;

    LinearLayoutManager linearLayoutManager;
    ArrayList<TransactionDetailsData> transactionList;
    TransactionDetailsAdapter transactionDetailsAdapter;
    RecyclerView recyclerView;

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
                String selectedText = items[which].toString();
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

    }
    private void detailDeposit() {

    }
    private void detailWithDraw() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        transactionList.clear();
        TransactionDetailsData transactionDetailsData = new TransactionDetailsData("8-25", "임세현", "8:40",
                "send", "-10", "200,000");
        transactionList.add(transactionDetailsData);
        transactionDetailsAdapter.notifyDataSetChanged();
    }
}