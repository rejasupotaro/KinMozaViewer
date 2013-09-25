package com.rejasupotaro.utils;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class WindowUtils {

    private static final String TAG = WindowUtils.class.getSimpleName();

    public static Point getSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();

        getSize(display, metrics, size);

        return size;
    }

    public static void getSize(Display display, DisplayMetrics metrics, Point outSize) {
        try {
            Class pointClass = Class.forName("android.graphics.Point");
            Method getSizeMethod = Display.class.getMethod("getSize", new Class[]{pointClass});

            getSizeMethod.invoke(display, outSize);
        } catch (NoSuchMethodException ex) {
            outSize.x = display.getWidth();
            outSize.y = display.getHeight();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e(TAG, e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.getMessage());
        }

        outSize.x /= metrics.scaledDensity;
        outSize.y /= metrics.scaledDensity;
    }

    private WindowUtils() {
    }
}
