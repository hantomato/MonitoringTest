package com.nmj.monitoringtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.nmj.monitoringtest.util.Logcat;
import com.nmj.monitoringtest.util.Logger;

public class MonitoringService extends Service {
    Thread mThd;
    String mainProcessPid;

    public MonitoringService() {
        Logger.logd("MonitoringService create");
        Logger.makeLogAboutProcessInfo("MonitoringService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.logd("MonitoringService onBind");
        return IMonitor;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.logd("MonitoringService onUnbind");
        startLogThread();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.logd("MonitoringService onDestroy");
    }

    IMonitoringService.Stub IMonitor = new IMonitoringService.Stub() {

        @Override
        public void setMainProcessPid(String pId) throws RemoteException {
            Logger.logd("MonitoringService setMainProcessPid : " + pId);
            mainProcessPid = pId;
        }

        @Override
        public void test1() throws RemoteException {

        }

        @Override
        public void test2() throws RemoteException {

        }
    };

    private void startLogThread() {
        // start thread
        if (mThd == null) {
            mThd = new Thread(new Runnable() {
                @Override
                public void run() {
                    Logger.logi("Thread start ========================================== : " + mainProcessPid);
                    Logger.logi("log : " + Logcat.getLog(mainProcessPid).toString());
                    Logger.logi("Thread end ==========================================");
                }
            });
            mThd.start();
        }
    }

    private void extractLatestLogcat() {
        Logger.logd("MonitoringService. following log is latest log ======================");
        Logcat.getLog(mainProcessPid);
        Logger.logd("MonitoringService. log end ==========================================");
    }
}
