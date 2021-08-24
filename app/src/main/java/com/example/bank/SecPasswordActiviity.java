package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SecPasswordActiviity extends AppCompatActivity {

    public static String secPassword;

    TextView tv_one;
    TextView tv_two;
    TextView tv_three;
    TextView tv_four;
    TextView tv_five;
    TextView tv_six;
    TextView tv_seven;
    TextView tv_eight;
    TextView tv_nine;
    TextView tv_zero;
    ImageView ib_suBack;

    ImageView ig_d1;
    ImageView ig_d2;
    ImageView ig_d3;
    ImageView ig_d4;

    int clickNum;
    String secPwArr[] = new String[5];
    public static int secPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_password);

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

            String secPwString = secPwArr[1] + secPwArr[2] + secPwArr[3] + secPwArr[4];

            secPw = Integer.parseInt(secPwString);

            certified();
        }
    }

    private void certified() {
        // 맞는지 틀린지 검사
        finish();
    }
}