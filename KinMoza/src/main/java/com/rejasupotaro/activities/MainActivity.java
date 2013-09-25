package com.rejasupotaro.activities;

import android.app.Activity;
import android.webkit.WebView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rejasupotaro.R;
import com.rejasupotaro.constants.Param;
import com.rejasupotaro.utils.UrlUtils;

@EActivity(R.layout.activity_main)
@NoTitle
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @ViewById(R.id.webview_kinmoza)
    WebView mKinmozaWebView;

    @AfterViews
    void initKinmozaWebView() {
        loadAlice();
        mKinmozaWebView.zoomIn();
    }

    private void loadAlice() {
        loadAlice(Param.UNDEFINED, Param.UNDEFINED);
    }

    private void loadAlice(int ep, int no) {
        mKinmozaWebView.loadUrl(UrlUtils.buildAliceUrl(ep, no));
    }
}
