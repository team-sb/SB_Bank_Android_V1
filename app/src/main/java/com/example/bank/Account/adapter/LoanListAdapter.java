package com.example.bank.Account.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.Account.data.LoanListData;
import com.example.bank.R;

import java.util.ArrayList;

public class LoanListAdapter extends RecyclerView.Adapter<LoanListAdapter.CustomViewHolder>{

    Context Ctx;
    ArrayList<LoanListData> arraylist;

    public LoanListAdapter(ArrayList<LoanListData> arraylist, Context Ctx) {
        this.arraylist = arraylist;
        this.Ctx = Ctx;
    }

    @NonNull
    @Override
    public LoanListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoanListAdapter.CustomViewHolder holder, int position) {
        holder.view_tv_borrowedDate.setText("" + arraylist.get(position).getBorrowedDate());
        holder.view_tv_money.setText("" + arraylist.get(position).getMoney());
        holder.view_tv_interest.setText("" + arraylist.get(position).getInterest());
        holder.view_tv_loanExpirationDate.setText("" + arraylist.get(position).getLoanExpirationDate());
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView view_tv_borrowedDate;
        protected TextView view_tv_money;
        protected TextView view_tv_interest;
        protected TextView view_tv_loanExpirationDate;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view_tv_borrowedDate = (TextView) itemView.findViewById(R.id.view_tv_borrowedDate);
            this.view_tv_money = (TextView) itemView.findViewById(R.id.view_tv_money);
            this.view_tv_interest = (TextView) itemView.findViewById(R.id.view_tv_interest);
            this.view_tv_loanExpirationDate = (TextView) itemView.findViewById(R.id.view_tv_loanExpirationDate);
        }
    }
}
