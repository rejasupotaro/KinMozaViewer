package com.rejasupotaro.activities;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rejasupotaro.R;
import com.rejasupotaro.listeners.AliceSeekBarChangeListener;
import com.rejasupotaro.tasks.MediaTimerTask;
import com.rejasupotaro.templates.AliceTemplate;
import com.rejasupotaro.templates.Template;
import com.rejasupotaro.utils.WindowUtils;
import com.rejasupotaro.views.AliceWebView;

import android.app.Activity;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

@EActivity(R.layout.activity_main)
@NoTitle
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @AfterViews
    void initViews() {
        keepScreenOn();
        initAliceWebView();
        initSeekBars();
        initMediaControllButton();
    }

    private void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @ViewById(R.id.webview_kinmoza)
    AliceWebView mAliceWebView;

    private void initAliceWebView() {
        Point point = WindowUtils.getSize(this);
        Template template = new AliceTemplate(this, point.x, point.y);

        mAliceWebView.setTemplate(template);
        mAliceWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
        mAliceWebView.loadAlice(1, 1);
    }

    @ViewById(R.id.seekbar)
    SeekBar mSeekBar;

    private void initSeekBars() {
        AliceSeekBarChangeListener onSeekBarChangeListener =
                new AliceSeekBarChangeListener(mSeekBar, mSeekBarEventListener);

        mSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private AliceSeekBarChangeListener.Event mSeekBarEventListener =
            new AliceSeekBarChangeListener.Event() {
                @Override
                public void onChanged(int ep, int no) {
                    mAliceWebView.loadAlice(ep, no);
                }
            };

    private MediaTimerTask mTimerTask;

    @ViewById(R.id.media_controll_button)
    CheckBox mMediaControllButton;

    private void initMediaControllButton() {
        mTimerTask = new MediaTimerTask(mSeekBar);

        mMediaControllButton
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mTimerTask.start();
                        } else {
                            mTimerTask.stop();
                        }
                    }
                });
    }
}
