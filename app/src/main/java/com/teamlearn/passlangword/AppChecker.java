package com.teamlearn.passlangword;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import android.os.Handler;
import android.os.Message;

public class AppChecker extends Activity {

    private static final int PERIOD_IN_MILLIS = 1000; // check every second
    private String pril;
    private Handler mHandler;

    public AppChecker(String pril) {
        this.pril = pril; // package name to check

        mHandler = new Handler();
        checkRunningApp();
    }

    public void checkRunningApp() {
        Runnable checkRunningTask = new Runnable() {
            @Override
            public void run() {
                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(100);

                for (ActivityManager.RunningTaskInfo taskInfo : runningTasks) {
                    if (taskInfo.topActivity.getClassName().equals(pril)) {
                        Log.e("AppChecker", "Application is running");
                    } else {
// application is not running, check again in “PERIOD_IN_MILLIS” milliseconds
                        mHandler.postDelayed(this, PERIOD_IN_MILLIS);
                    }
                }
            }
        };

        mHandler.post(checkRunningTask);
    }
}
