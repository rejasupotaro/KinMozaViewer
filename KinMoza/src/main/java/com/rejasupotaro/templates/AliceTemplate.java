package com.rejasupotaro.templates;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.rejasupotaro.utils.CloseableUtils;

import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AliceTemplate implements Template {

    private static final String TAG = AliceTemplate.class.getSimpleName();

    private static final String FILE_NAME = "alice.html";

    private String mContent;

    private int mWidth;

    private int mHeight;

    public AliceTemplate(Context context, int width, int height) {
        mWidth = width;
        mHeight = height;
        mContent = loadContent(context);
    }

    private String loadContent(Context context) {
        InputStream in = null;
        BufferedReader reader = null;

        try {
            AssetManager as = context.getResources().getAssets();
            in = as.open(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(in, HTTP.UTF_8));

            StringBuffer stringBugger = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                stringBugger.append(str);
            }

            return stringBugger.toString();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            CloseableUtils.close(reader);
            CloseableUtils.close(in);
        }

        return "";
    }

    public String compile(String imageUrl) {
        String content = mContent;

        content = content.replace("#{image_width}", Integer.toString(mWidth));
        content = content.replace("#{image_height}", Integer.toString(mHeight));
        content = content.replace("#{image_url}", imageUrl);

        return content;
    }
}
