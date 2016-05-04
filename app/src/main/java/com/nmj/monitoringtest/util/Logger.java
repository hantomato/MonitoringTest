package com.nmj.monitoringtest.util;

/**
 * Created by nmj on 16. 5. 4..
 */
public class Logger {
    public static final String TAG = "nmj9";

    public static void logd(String s) {
        android.util.Log.d(TAG, s);
    }
    public static void logi(String s) {
        android.util.Log.i(TAG, s);
    }

    public static void loge(String s) {
        android.util.Log.e(TAG, s);
    }

    public static void makeLogAboutProcessInfo(String prefix) {
        String log = String.format("%s pid-tid-uid : %d-%d-%d", prefix,
                android.os.Process.myPid(),
                android.os.Process.myTid(),
                android.os.Process.myUid());
        android.util.Log.d(TAG, log);
    }

}
