package com.rejasupotaro.activities;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.SeekBar;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rejasupotaro.R;
import com.rejasupotaro.constants.Param;
import com.rejasupotaro.listeners.AliceSeekBarChangeListener;
import com.rejasupotaro.utils.UrlUtils;

@EActivity(R.layout.activity_main)
@NoTitle
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @ViewById(R.id.webview_kinmoza)
    WebView mKinmozaWebView;

    @ViewById(R.id.seekbar_ep)
    SeekBar mEpSeekBar;

    @ViewById(R.id.seekbar_no)
    SeekBar mNoSeekBar;

    @AfterViews
    void initKinmozaWebView() {
        loadAlice();
        mKinmozaWebView.zoomIn();
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
                    loadAlice(ep, no);
                }
            };

    private void loadAlice() {
        loadAlice(Param.UNDEFINED, Param.UNDEFINED);
    }

    private void loadAlice(int ep, int no) {
        mKinmozaWebView.loadUrl(UrlUtils.buildAliceUrl(ep, no));
    }
}
