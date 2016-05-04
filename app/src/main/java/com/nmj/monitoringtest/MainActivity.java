package com.nmj.monitoringtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                btn1();
                break;
            case R.id.button2:
                btn2();
                break;
            case R.id.button3:
                btn3();
                break;
            case R.id.button4:
                btn4();
                break;
            case R.id.button5:
                btn5();
                break;
        }
    }

    public void btn1() {
        startActivity(new Intent(this, LogcatViewActivity.class));
    }

    public void btn2() {

    }

    public void btn3() {

    }

    public void btn4() {

    }

    public void btn5() {

    }

}
