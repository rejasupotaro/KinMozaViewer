package com.rejasupotaro.test.utils;

import android.test.AndroidTestCase;

import com.rejasupotaro.constants.Param;
import com.rejasupotaro.constants.Url;
import com.rejasupotaro.utils.UrlUtils;

public class UrlUtilsTest extends AndroidTestCase {

    public void testBuildAliceUrl() {
        {
            String aliceUrl = UrlUtils.buildAliceUrl(1, Param.UNDEFINED);
            assertEquals(Url.ALICE + "?ep=1", aliceUrl);
        }
        {
            String aliceUrl = UrlUtils.buildAliceUrl(Param.UNDEFINED, 1);
            assertEquals(Url.ALICE + "?no=1", aliceUrl);
        }
        {
            String aliceUrl = UrlUtils.buildAliceUrl(1, 1);
            assertEquals(Url.ALICE + "?ep=1&no=1", aliceUrl);
        }
    }
}
