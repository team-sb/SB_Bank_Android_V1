package com.example.bank;

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
    Call<RegisterResponse> Register(@Body RegisterRequest registerRequest);

    @POST("auth/sec-login") // 2차 로그인
    Call<SecPasswordResponse> SecLogin(@Body int sec_password);

    @POST("auth/password") // 비밀번호 수정
    Call<Void> PasswordChange(@Body String password, @Body String new_password);

    // Account

    @POST("account/register") // 계좌 생성
    Call<AccountResponse> createAccount(@Header("Authorization") String token);

    @POST("account/charge") // 입출금
    Call<Void> chargeAccount(@Header("Authorization") String token, @Body int money);

    @POST("account/transfer") // 계좌이체
    Call<Void> transferAccount(@Header("Authorization") String token, @Body String money, @Body String target);

    @POST("account/borrow") // 대출
    Call<AccountBorrowResponse> borrowAccount(@Header("Authorization") String token, @Body String money);

    // MainPage

    @GET("user/balance") // 잔액 표시
    Call<UserBalanceResponse> balanceMain(@Header("Authorization") String token);

    @GET("user/transaction") // 거래 내역(전체)
    Call<MainPageRequest> transactionMain(@Header("Authorization") String token);

    @GET("user/transaction/send") // 거래 내역(입금)
    Call<MainPageRequest> transactionSendMain(@Header("Authorization") String token);

    @GET("user/transaction/receive") // 거래 내역(출금)
    Call<MainPageRequest> transactionReceiveMain(@Header("Authorization") String token);

    // UserPage

    @GET("user/balance") // 잔액 표시
    Call<MainPageRequest> balanceUser(@Header("Authorization") String token);

    @GET("user/loan") // 대출 현황
    Call<MainPageRequest> loanUser(@Header("Authorization") String token);

    @GET("user/credit") // 신용 등급
    Call<MainPageRequest> creditUser(@Header("Authorization") String token);

    @GET("user/transaction") // 거래 내역(전체)
    Call<MainPageRequest> transactionUser(@Header("Authorization") String token);

    @GET("user/transaction/send") // 거래 내역(입금)
    Call<MainPageRequest> transactionSendUser(@Header("Authorization") String token);

    @GET("user/transaction/receive") // 거래 내역(출금)
    Call<MainPageRequest> transactionReceiveUser(@Header("Authorization") String token);
}
