package com.rejasupotaro.utils;

import java.io.Closeable;
import java.io.IOException;

public final class CloseableUtils {

    public static void close(Closeable res) {
        if (res == null) return;
        try {
            res.close();
        } catch (IOException e) {
            // ignore
        }
    }

    private CloseableUtils() {}
}
