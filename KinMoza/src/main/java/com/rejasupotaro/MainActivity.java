package com.rejasupotaro;

import android.app.Activity;
import android.net.UrlQuerySanitizer;
import android.webkit.WebView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

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
        loadAlice(-1, -1);
    }

    private void loadAlice(int ep, int no) {
        mKinmozaWebView.loadUrl(buildAliceUrl(ep, no));
    }

    private String buildAliceUrl(int ep, int no) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (ep >= 0) {
            params.add(new BasicNameValuePair("ep", Integer.toString(ep)));
        }
        if (no >= 0) {
            params.add(new BasicNameValuePair("no", Integer.toString(no)));
        }

        String paramString = URLEncodedUtils.format(params, HTTP.UTF_8);

        return "http://mogashi.no-ip.org/alice/" + paramString;
    }
}
