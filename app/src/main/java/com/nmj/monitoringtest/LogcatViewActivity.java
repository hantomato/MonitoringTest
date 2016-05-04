package com.nmj.monitoringtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nmj.monitoringtest.util.Logcat;

import java.io.IOException;

/**
 * Created by nmj on 16. 5. 4..
 */
public class LogcatViewActivity extends AppCompatActivity {
    private static final String TAG = Logcat.class.getCanonicalName();

    TextView tvLog;
    String strLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logcat);
        tvLog = (TextView) findViewById(R.id.textView);
        tvLog.setMovementMethod(new ScrollingMovementMethod());
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
        }
    }

    public void btn1() {
        Thread thd = new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = Logcat.getLog(null);
                Log.d(TAG, "sb size : " + sb.length());
                strLog = sb.toString();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvLog.setText(strLog);
                    }
                });
            }
        });
        thd.start();
    }

    public void btn2() {
        // clear log
        try {
            Process process = new ProcessBuilder()
                    .command("logcat", "-c")
                    .redirectErrorStream(true)
                    .start();
        } catch (IOException e) {
        }
    }

    public void btn3() {

    }
}