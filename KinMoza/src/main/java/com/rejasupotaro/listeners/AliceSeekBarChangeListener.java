package com.rejasupotaro.listeners;

import android.widget.SeekBar;

public class AliceSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private static final int PROGRESS_RATIO = 100;

    private static final int MAX_EP = 6;

    private static final int MAP_NO = 6;

    private static final int MAX_SCENE = MAX_EP * MAX_EP;

    private SeekBar mSeekBar;

    private Event mEventListener;

    private int mPrevScene = 0;

    public AliceSeekBarChangeListener(SeekBar seekBar, Event eventListener) {
        mSeekBar = seekBar;
        mEventListener = eventListener;
        mSeekBar.setMax(MAX_SCENE * PROGRESS_RATIO);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        update(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // nothing to do
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        update(mSeekBar.getProgress());
    }

    private void update(int progress) {
        int scene = normarizeProgress(progress);
        if (!isProgressChanged(scene)) return;
        mPrevScene = scene;

        int ep = (scene / MAX_EP) + 1;
        int no = (scene % MAP_NO) + 1;

        mEventListener.onChanged(ep, no);
    }

    private int normarizeProgress(int progress) {
        return (int) Math.ceil((progress / PROGRESS_RATIO) + 1);
    }

    private boolean isProgressChanged(int scene) {
        return (scene != mPrevScene);
    }

    public interface Event {
        public void onChanged(int ep, int no);
    }
}
