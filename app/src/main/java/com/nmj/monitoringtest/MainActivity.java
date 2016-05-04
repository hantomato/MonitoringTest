package com.nmj.monitoringtest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nmj.monitoringtest.util.Logger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    IMonitoringService mMonitorBinder;
    ArrayList<String> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.makeLogAboutProcessInfo("MainProcess");
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
            case R.id.button6:
                btn6();
                break;
            case R.id.button7:
                btn7();
                break;
        }
    }

    public void btn1() {
        // popup LogcatViewActivity
        startActivity(new Intent(this, LogcatViewActivity.class));
    }

    public void btn2() {
        // bindService
        if (mMonitorBinder == null) {
            Intent i = new Intent();
            i.setComponent(new ComponentName(getPackageName(), MonitoringService.class.getName()));
            boolean ret = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
            Logger.logd("MainActivity. bindService ret: " + ret);
        }
    }

    public void btn3() {
        Logger.logd("MainActivity. log - this is test log");
    }

    public void btn4() {
        if (null != mMonitorBinder) {
            try {
                mMonitorBinder.setMainProcessPid(android.os.Process.myPid() + "");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void btn5() {
        Logger.logd("MainActivity. make crash..");
        temp.size();
    }

    public void btn6() {

    }

    public void btn7() {
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Logger.logd("MainActivity. onServiceConnected");
            mMonitorBinder = IMonitoringService.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Logger.logd("MainActivity. onServiceDisconnected");
            mMonitorBinder = null;
        }
    };

}
