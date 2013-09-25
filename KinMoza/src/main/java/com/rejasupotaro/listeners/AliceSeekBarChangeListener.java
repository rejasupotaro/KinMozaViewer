package com.rejasupotaro.listeners;

import android.widget.SeekBar;

public class AliceSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private static final int PROGRESS_RATIO = 100;

    private static final int MAX_EP = 6;

    private static final int MAX_NO = 6;

    private SeekBar mEpSeekBar;

    private SeekBar mNoSeekBar;

    private Event mEventListener;

    private int mPrevEp = 0;

    private int mPrevNo = 0;

    public AliceSeekBarChangeListener(SeekBar epSeekBar, SeekBar noSeekBar, Event eventListener) {
        mEpSeekBar = epSeekBar;
        mNoSeekBar = noSeekBar;
        mEventListener = eventListener;

        mEpSeekBar.setMax(MAX_EP * PROGRESS_RATIO);
        mNoSeekBar.setMax(MAX_NO * PROGRESS_RATIO);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // nothing to do
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // nothing to do
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int ep = normarizeProgress(mEpSeekBar.getProgress());
        int no = normarizeProgress(mNoSeekBar.getProgress());

        if (!isProgressChanged(ep, no)) return;

        mPrevEp = ep;
        mPrevNo = no;
        mEventListener.onChanged(ep, no);
    }

    private int normarizeProgress(int progress) {
        return (int) Math.ceil((progress / PROGRESS_RATIO) + 1);
    }

    private boolean isProgressChanged(int ep, int no) {
        return ((ep != mPrevEp) || (no != mPrevNo));
    }

    public interface Event {
        public void onChanged(int ep, int no);
    }
}
