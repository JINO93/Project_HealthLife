package com.jino.healthLife;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.jino.healthLife.hl.Constane.Constant;
import com.jino.healthLife.hl.net.Request;
import com.jino.healthLife.hl.net.RequestCallBack;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void requestUtilTest(){
        Request.getInstance().get(Constant.NEWS_CATEGORY_URL, new RequestCallBack() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable ex) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}