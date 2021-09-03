package com.example.bank;

import com.example.bank.Account.data.AccountLoanResponse;
import com.example.bank.Account.data.AccountResponse;
import com.example.bank.Account.data.LoanListResponse;
import com.example.bank.Account.data.SendRequest;
import com.example.bank.Admin.data.UserInfoResponse;
import com.example.bank.Admin.data.UserListResponse;
import com.example.bank.Auth.data.LoginRequest;
import com.example.bank.Auth.data.LoginResponse;
import com.example.bank.Auth.data.RegisterRequest;
import com.example.bank.Auth.data.SecPasswordResponse;
import com.example.bank.Main.data.MainPageRequest;
import com.example.bank.Main.data.MainTranscationResponse;
import com.example.bank.User.data.UserBalanceResponse;
import com.example.bank.User.data.UserDeleteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerAPI {

    // Auth

    @POST("auth/login") // 로그인
    Call<LoginResponse> Login(@Body LoginRequest loginRequest);

    @POST("auth/join") // 회원가입
    Call<Void> Register(@Body RegisterRequest registerRequest);

    @POST("auth/sec-login") // 2차 로그인
    Call<SecPasswordResponse> SecLogin(@Header("Authorization") String token, @Body String sec_password);

    @POST("auth/password") // 비밀번호 수정
    Call<Void> PasswordChange(@Body String password, @Body String new_password);

    @DELETE("auth/exit/{id}") // 회원탈퇴
    Call<UserDeleteResponse> deleteAccount(@Header("Authorization") String token, @Path("id") String id);

    // Account

    @POST("account/register") // 계좌 생성
    Call<AccountResponse> createAccount(@Header("Authorization") String token);

    @POST("account/charge") // 입출금
    Call<Void> chargeAccount(@Header("Authorization") String token, @Body int money);

    @POST("account/transfer") // 계좌이체
    Call<Void> transferAccount(@Header("Authorization") String token, @Body SendRequest sendRequests);

    @POST("account/loan") // 대출
    Call<AccountLoanResponse> accountLoan(@Header("Authorization") String token, @Body String money);

    // MainPage

    @GET("user/balance") // 잔액 표시
    Call<UserBalanceResponse> balanceMain(@Header("Authorization") String token);

    @GET("user/transaction") // 거래 내역(전체)
    Call<MainTranscationResponse> transactionMain(@Header("Authorization") String token);

    @GET("user/transaction/send") // 거래 내역(입금)
    Call<MainTranscationResponse> transactionSendMain(@Header("Authorization") String token);

    @GET("user/transaction/receive") // 거래 내역(출금)
    Call<MainTranscationResponse> transactionReceiveMain(@Header("Authorization") String token);

    // UserPage

    @GET("user/balance") // 잔액 표시
    Call<MainPageRequest> balanceUser(@Header("Authorization") String token);

    @GET("user/loan") // 대출 현황
    Call<LoanListResponse> loanUser(@Header("Authorization") String token);

    @GET("user/transaction") // 거래 내역(전체)
    Call<MainTranscationResponse> transactionUser(@Header("Authorization") String token);

    @GET("user/transaction/send") // 거래 내역(입금)
    Call<MainTranscationResponse> transactionSendUser(@Header("Authorization") String token);

    @GET("user/transaction/receive") // 거래 내역(출금)
    Call<MainTranscationResponse> transactionReceiveUser(@Header("Authorization") String token);

    // Admin
    @GET("admin/users") // 잔액 표시
    Call<UserListResponse> userList(@Header("Authorization") String token);

    @GET("admin/users/{user-id}") // 잔액 표시
    Call<UserInfoResponse> userInfo(@Header("Authorization") String token, @Path("user-id") String user_id);

    @PATCH("admin/users/{user-id}/balance") // 잔액 표시
    Call<Void> editBalance(@Header("Authorization") String token, @Path("user-id") String user_id, @Body int money);
}
