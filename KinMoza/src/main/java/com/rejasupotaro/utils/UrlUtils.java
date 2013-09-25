package com.rejasupotaro.utils;

import android.text.TextUtils;

import com.rejasupotaro.constants.Url;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

public final class UrlUtils {

    public static String buildAliceUrl(int ep, int no) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (ep >= 0) {
            params.add(new BasicNameValuePair("ep", Integer.toString(ep)));
        }
        if (no >= 0) {
            params.add(new BasicNameValuePair("no", Integer.toString(no)));
        }

        String paramString = URLEncodedUtils.format(params, HTTP.UTF_8);

        String urlString = Url.ALICE;
        if (!TextUtils.isEmpty(paramString)) {
            urlString += "?" + paramString;
        }

        return urlString;
    }

    private UrlUtils() {}
}
