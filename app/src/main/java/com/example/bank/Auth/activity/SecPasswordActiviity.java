package com.example.bank.Auth.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank.ApiProvider;
import com.example.bank.R;
import com.example.bank.Auth.data.SecPasswordResponse;
import com.example.bank.ServerAPI;
import com.example.bank.UserData;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecPasswordActiviity extends AppCompatActivity {

    private static final String TAG = "SecPasswordActiviity";

    public static String secPassword;

    private ProgressBar secPassword_progressBar;

    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private TextView tv_four;
    private TextView tv_five;
    private TextView tv_six;
    private TextView tv_seven;
    private TextView tv_eight;
    private TextView tv_nine;
    private TextView tv_zero;
    private ImageView ib_suBack;

    ImageView ig_d1;
    ImageView ig_d2;
    ImageView ig_d3;
    ImageView ig_d4;

    ImageButton secPassword_ib_back;

    int clickNum = 0;
    String secPwArr[] = new String[20];
    public static String secPw;
    public static boolean secSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_password);

        secPassword_progressBar = (ProgressBar) findViewById(R.id.secPassword_progressBar);

        secPassword_ib_back = (ImageButton) findViewById(R.id.secPassword_ib_back);
        secPassword_ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        tv_four = (TextView) findViewById(R.id.tv_four);
        tv_five = (TextView) findViewById(R.id.tv_five);
        tv_six = (TextView) findViewById(R.id.tv_six);
        tv_seven = (TextView) findViewById(R.id.tv_seven);
        tv_eight = (TextView) findViewById(R.id.tv_eight);
        tv_nine = (TextView) findViewById(R.id.tv_nine);
        tv_zero = (TextView) findViewById(R.id.tv_zero);
        ib_suBack = (ImageView) findViewById(R.id.ib_suBack);

        ig_d1 = (ImageView) findViewById(R.id.ig_d1);
        ig_d2 = (ImageView) findViewById(R.id.ig_d2);
        ig_d3 = (ImageView) findViewById(R.id.ig_d3);
        ig_d4 = (ImageView) findViewById(R.id.ig_d4);


        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("1");
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("2");
            }
        });
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("3");
            }
        });
        tv_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("4");
            }
        });
        tv_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("5");
            }
        });
        tv_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("6");
            }
        });
        tv_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("7");
            }
        });
        tv_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("8");
            }
        });
        tv_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("9");
            }
        });
        tv_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                clickButton("0");
            }
        });
        ib_suBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickNum >= 1) {
                    clickNum--;
                    clickButton("no");
                }
            }
        });
    }

    private void clickButton(String clickSu) {
        if(!(Objects.equals(clickSu, "no"))) {
            secPwArr[clickNum] = clickSu;
        }

        if(clickNum == 0) {
            ig_d1.setImageResource(R.drawable.emptymoney);
            ig_d2.setImageResource(R.drawable.emptymoney);
            ig_d3.setImageResource(R.drawable.emptymoney);
            ig_d4.setImageResource(R.drawable.emptymoney);
        }
        if(clickNum == 1) {
            ig_d1.setImageResource(R.drawable.dot);
            ig_d2.setImageResource(R.drawable.emptymoney);
            ig_d3.setImageResource(R.drawable.emptymoney);
            ig_d4.setImageResource(R.drawable.emptymoney);
        }
        if(clickNum == 2) {
            ig_d1.setImageResource(R.drawable.dot);
            ig_d2.setImageResource(R.drawable.dot);
            ig_d3.setImageResource(R.drawable.emptymoney);
            ig_d4.setImageResource(R.drawable.emptymoney);
        }
        if(clickNum == 3) {
            ig_d1.setImageResource(R.drawable.dot);
            ig_d2.setImageResource(R.drawable.dot);
            ig_d3.setImageResource(R.drawable.dot);
            ig_d4.setImageResource(R.drawable.emptymoney);
        }
        if(clickNum == 4) {
            ig_d1.setImageResource(R.drawable.dot);
            ig_d2.setImageResource(R.drawable.dot);
            ig_d3.setImageResource(R.drawable.dot);
            ig_d4.setImageResource(R.drawable.dot);

            secPw = secPwArr[1] + secPwArr[2] + secPwArr[3] + secPwArr[4];

            Log.d(TAG, "clickButton: " + secPw);
            certified();
        }
    }

    private void certified() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        String bearerUserToken = "Bearer " + UserData.user_token;
        Call<SecPasswordResponse> call = serverAPI.SecLogin(bearerUserToken, secPw);

        secPassword_progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<SecPasswordResponse>() {
            @Override
            public void onResponse(Call<SecPasswordResponse> call, Response<SecPasswordResponse> response) {
                secPassword_progressBar.setVisibility(View.GONE);

                int result = response.code();
                if(result == 200) {
                    UserData.sec_token = response.body().getSecToken();
                    UserData.temp_token = UserData.sec_token;
                    secSuccess = true;
                    finish();
                } else if (result == 401) {
                    Toast.makeText(SecPasswordActiviity.this, "2차 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();

                    // 초기화
                    clearInput();
                } else {
                    Toast.makeText(SecPasswordActiviity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SecPasswordResponse> call, Throwable t) {
                secPassword_progressBar.setVisibility(View.GONE);

                Toast.makeText(SecPasswordActiviity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearInput() {
        clickNum = 0;

        ig_d1.setImageResource(R.drawable.emptymoney);
        ig_d2.setImageResource(R.drawable.emptymoney);
        ig_d3.setImageResource(R.drawable.emptymoney);
        ig_d4.setImageResource(R.drawable.emptymoney);
    }
}