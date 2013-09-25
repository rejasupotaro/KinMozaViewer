package com.rejasupotaro.activities;

import android.app.Activity;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rejasupotaro.R;
import com.rejasupotaro.listeners.AliceSeekBarChangeListener;
import com.rejasupotaro.templates.AliceTemplate;
import com.rejasupotaro.templates.Template;
import com.rejasupotaro.utils.WindowUtils;
import com.rejasupotaro.views.AliceWebView;

@EActivity(R.layout.activity_main)
@NoTitle
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @ViewById(R.id.webview_kinmoza)
    AliceWebView mAliceWebView;

    @ViewById(R.id.seekbar_ep)
    SeekBar mEpSeekBar;

    @ViewById(R.id.seekbar_no)
    SeekBar mNoSeekBar;

    @AfterViews
    void initKinmozaWebView() {
        Point point = WindowUtils.getSize(this);
        Template template = new AliceTemplate(this, point.x, point.y);

        mAliceWebView.setTemplate(template);
        mAliceWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
        mAliceWebView.loadAlice();
    }

    @AfterViews
    void initSeekBars() {
        AliceSeekBarChangeListener onSeekBarChangeListener =
                new AliceSeekBarChangeListener(mEpSeekBar, mNoSeekBar, mSeekBarEventListener);

        mEpSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        mNoSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private AliceSeekBarChangeListener.Event mSeekBarEventListener =
            new AliceSeekBarChangeListener.Event() {
                @Override
                public void onChanged(int ep, int no) {
                    mAliceWebView.loadAlice(ep, no);
                }
            };
}
