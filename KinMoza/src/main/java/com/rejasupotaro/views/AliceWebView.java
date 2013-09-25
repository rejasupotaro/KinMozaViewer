package com.rejasupotaro.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rejasupotaro.constants.Param;
import com.rejasupotaro.templates.Template;
import com.rejasupotaro.utils.UrlUtils;

import org.apache.http.protocol.HTTP;

public class AliceWebView extends WebView {

    private Template mTemplate;

    public AliceWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    public void setTemplate(Template template) {
        mTemplate = template;
    }

    public void loadAlice() {
        loadAlice(Param.UNDEFINED, Param.UNDEFINED);
    }

    public void loadAlice(int ep, int no) {
        if (mTemplate == null) return;

        String imageUrl = UrlUtils.buildAliceUrl(ep, no);
        String content = mTemplate.compile(imageUrl);
        loadDataWithBaseURL("", content, "text/html", HTTP.UTF_8, null);
    }
}
