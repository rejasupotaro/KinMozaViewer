package com.rejasupotaro.tasks;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class PeriodicTimerTask {

    private Handler mHandler = new Handler();

    private Runnable mTask;

    private int mTaskIntervalMs;

    private TimerTask mTimerTask;

    private Timer mTimer;

    private TimerTask newTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                mHandler.post(mTask);
            }
        };
    }

    private Timer newTimer() {
        return new Timer(true);
    }

    public PeriodicTimerTask(Runnable task, int taskIntervalMs) {
        mTask = task;
        mTaskIntervalMs = taskIntervalMs;
    }

    public void start() {
        if (mTimer == null) {
            mTimerTask = newTimerTask();
            mTimer = newTimer();
            mTimer.schedule(mTimerTask, 0, mTaskIntervalMs);
        }
    }

    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

}
