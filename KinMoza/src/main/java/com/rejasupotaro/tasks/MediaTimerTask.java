package com.rejasupotaro.tasks;

import android.os.Handler;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MediaTimerTask {

    private static final int TASK_INTERVAL_MS = 100;

    private static final int PROGRESS_INCREMENT = 2;

    private Handler mHandler = new Handler();

    private SeekBar mSeekBar;

    private TimerTask mTimerTask;

    private Timer mTimer;

    private TimerTask newTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    public void run() {
                        if (mSeekBar == null) {
                            return;
                        }

                        int progress = mSeekBar.getProgress();
                        if (progress >= mSeekBar.getMax()) {
                            progress = 0;
                        } else {
                            progress += PROGRESS_INCREMENT;
                        }
                        mSeekBar.setProgress(progress);
                    }
                });
            }
        };
    }

    private Timer newTimer() {
        return new Timer(true);
    }

    public MediaTimerTask(SeekBar seekBar) {
        super();
        mSeekBar = seekBar;
    }

    public void setTimer() {
        if (mTimer == null) {
            mTimerTask = newTimerTask();
            mTimer = newTimer();
            mTimer.schedule(mTimerTask, 0, TASK_INTERVAL_MS);
        }
    }

    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}

