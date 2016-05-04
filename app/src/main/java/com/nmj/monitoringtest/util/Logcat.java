package com.nmj.monitoringtest.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nmj on 16. 5. 3..
 */
public class Logcat {

    private static final String TAG = Logcat.class.getCanonicalName();
    private static final String processId = Integer.toString(android.os.Process
            .myPid());

    public static StringBuilder getLog(String targetPid) {
        Logger.logd("getLog. pid:" + android.os.Process.myPid());
        Logger.logd("getLog. tid:" + android.os.Process.myTid());
        Logger.logd("getLog. uid:" + android.os.Process.myUid());
        Log.d(TAG, "pid : " + processId);

        StringBuilder builder = new StringBuilder();

        try {
//            String[] command = new String[] { "logcat", "-v", "threadtime" };
            String[] command = new String[] { "logcat", "-d", "threadtime" };   // dump and exit
//            String[] command = new String[] { "logcat", "-s", "nmj7" };

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (TextUtils.isEmpty(targetPid)) {
                    builder.append(line + "\n");
                } else {
//                    if (line.contains(targetPid)) {
                    if (line.contains(targetPid) && line.contains("AndroidRuntime")) {
                        builder.append(line + "\n");
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Log.e(TAG, "getLog failed", ex);
        }

        return builder;
    }
}
