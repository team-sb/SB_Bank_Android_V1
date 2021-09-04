package com.example.bank.Admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.Account.adapter.LoanListAdapter;
import com.example.bank.Account.data.LoanListData;
import com.example.bank.Admin.data.UserListData;
import com.example.bank.AdminUserInfoActivity;
import com.example.bank.R;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.CustomViewHolder>{
    private Context Ctx;
    private ArrayList<UserListData> arraylist;

    public static String userId;
    public static String name;
    public static String userName;
    public static String userAuthority;

    public UserListAdapter(ArrayList<UserListData> arraylist, Context Ctx) {
        this.arraylist = arraylist;
        this.Ctx = Ctx;
    }

    @NonNull
    @Override
    public UserListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view, parent, false);
        UserListAdapter.CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.CustomViewHolder holder, int position) {
        holder.tv_adminName.setText(arraylist.get(position).getName());
        holder.tv_adminUserName.setText(arraylist.get(position).getUserName());
        holder.tv_userId.setText("유저 고유값 : " + arraylist.get(position).getId());
        holder.tv_adminAuthority.setText("권한 : " + arraylist.get(position).getAuthority());

        holder.tv_adminInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = arraylist.get(position).getId();
                name = arraylist.get(position).getName();
                userName = arraylist.get(position).getUserName();
                userAuthority = arraylist.get(position).getAuthority();

                v.getContext().startActivity(new Intent(v.getContext().getApplicationContext(), AdminUserInfoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_adminName;
        protected TextView tv_adminUserName;
        protected TextView tv_userId;
        protected TextView tv_adminAuthority;

        protected TextView tv_adminInfo;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_adminName = (TextView) itemView.findViewById(R.id.tv_adminName);
            this.tv_adminUserName = (TextView) itemView.findViewById(R.id.tv_adminUserName);
            this.tv_userId = (TextView) itemView.findViewById(R.id.tv_userId);
            this.tv_adminAuthority = (TextView) itemView.findViewById(R.id.tv_adminAuthority);

            this.tv_adminInfo = (TextView) itemView.findViewById(R.id.tv_adminInfo);
        }
    }
}
