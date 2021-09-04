package com.example.bank.Main.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.Main.data.TransactionDetailsData;
import com.example.bank.R;

import java.util.ArrayList;
import java.util.Objects;

public class TransactionDetailsAdapter extends RecyclerView.Adapter<TransactionDetailsAdapter.CustomViewHolder> {

    private static final String TAG = "TransactionDetailsAdapt";

    private Context Ctx;
    private ArrayList<TransactionDetailsData> transactionList;
    int pos;

    public TransactionDetailsAdapter(ArrayList<TransactionDetailsData> transactionList, Context Ctx) {
        this.transactionList = transactionList;
        this.Ctx = Ctx;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_transactionDate.setText(transactionList.get(position).getTv_transactionDate());
        holder.tv_transactionName.setText(transactionList.get(position).getTv_transactionName().replace("\"", ""));
        holder.tv_transactionTime.setText(transactionList.get(position).getTv_transactionTime());
        holder.tv_transactionMoney.setText(transactionList.get(position).getTv_transactionMoney());
        holder.tv_transactionPrice.setText(transactionList.get(position).getTv_transactionPrice());

        if(Objects.equals(transactionList.get(position).getTv_transactionType(), "\"RECEIVE\"")) {
            holder.iv_transactionType.setImageResource(R.drawable.received);
        } else if(Objects.equals(transactionList.get(position).getTv_transactionType(), "\"SEND\"")) {
            holder.iv_transactionType.setImageResource(R.drawable.sent);
        } else if(Objects.equals(transactionList.get(position).getTv_transactionType(), "\"FAILED\"")) {
            holder.iv_transactionType.setImageResource(R.drawable.failed);
        }
        
    }

    @Override
    public int getItemCount() {
        return (null != transactionList ? transactionList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_transactionDate;
        protected TextView tv_transactionName;
        protected TextView tv_transactionTime;
        protected ImageView iv_transactionType;
        protected TextView tv_transactionMoney;
        protected TextView tv_transactionPrice;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_transactionDate = (TextView) itemView.findViewById(R.id.tv_transactionDate);
            this.tv_transactionName = (TextView) itemView.findViewById(R.id.tv_transactionName);
            this.tv_transactionTime = (TextView) itemView.findViewById(R.id.tv_transactionTime);
            this.iv_transactionType = (ImageView) itemView.findViewById(R.id.iv_transactionType);
            this.tv_transactionMoney = (TextView) itemView.findViewById(R.id.tv_transactionMoney);
            this.tv_transactionPrice = (TextView) itemView.findViewById(R.id.tv_transactionPrice);
        }
    }
}
