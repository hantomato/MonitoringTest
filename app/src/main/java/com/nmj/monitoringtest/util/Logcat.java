package com.nmj.monitoringtest.util;

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

    public static StringBuilder getLog() {
        Thread dd = Thread.currentThread();
        Log.d(TAG, "do getLog : " + dd.getId());

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
//                if (line.contains(processId)) { // 내 프로세스의 로그만 구하자.
                    builder.append(line + "\n");
                    // 필터링이 필요하다면 여기서 할수 있겠다.
//                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Log.e(TAG, "getLog failed", ex);
        }

        return builder;
    }
}
