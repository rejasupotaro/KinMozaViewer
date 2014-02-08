package com.rejasupotaro.tasks;

import android.widget.SeekBar;

public class MediaTimerTask extends PeriodicTimerTask {

    private static final int TASK_INTERVAL_MS = 500;

    private static final int PROGRESS_INCREMENT = 10;

    public MediaTimerTask(final SeekBar seekBar) {
        super(new Runnable() {
            @Override
            public void run() {
                if (seekBar == null) {
                    return;
                }

                int progress = seekBar.getProgress();
                if (progress >= seekBar.getMax()) {
                    progress = 0;
                } else {
                    progress += PROGRESS_INCREMENT;
                }
                seekBar.setProgress(progress);
            }
        }, TASK_INTERVAL_MS);
    }
}

