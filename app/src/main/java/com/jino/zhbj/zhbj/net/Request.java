package com.jino.zhbj.zhbj.net;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jino.zhbj.zhbj.models.ItemDetial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Request {


    private static final String RESULT_ARRAY = "yi18";
    private Gson gson;
    private Handler handler;

    private static Request mRequest = new Request();

    private Request() {
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    public static Request getInstance() {
        return mRequest;
    }

    public void get(String url, RequestCallBack callBack) {
        RequestParams mParams = buildParams(url, null);
//
        doRequest(mParams, RequestType.GET, callBack);

    }


    public void post(String url, Map<String, Object> params, RequestCallBack callBack) {
        RequestParams postParams = buildParams(url, params);
        doRequest(postParams, RequestType.POST, callBack);
    }

    private RequestParams buildParams(String url, Map<String, Object> params) {
        RequestParams mParams = new RequestParams(url);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> m : params.entrySet()) {
                mParams.addBodyParameter(m.getKey(), String.valueOf(m.getValue()));
            }
        }
        return mParams;
    }


    private void doRequest(RequestParams params, RequestType type, final RequestCallBack callBack) {

        callBack.onPreLoad();
        if (type == RequestType.GET) {
            x.http().get(params, new Callback.CommonCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {


                    successCallback(result, callBack);

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                    callBack.onError(ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        } else {
            x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    successCallback(result, callBack);

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    callBack.onError(ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }


    }

    private void successCallback(final JSONObject result, final RequestCallBack callBack) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack.mType == String.class) {
                    callBack.onSuccess(result.toString());
                } else if (callBack.mType == ItemDetial.class) {

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = result.getJSONObject(RESULT_ARRAY);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Object obj = gson.fromJson(jsonObject.toString(), callBack.mType);
                    callBack.onSuccess(obj);
                } else {
                    try {
                        JSONArray array = result.getJSONArray(RESULT_ARRAY);
                        Object obj = null;
                        obj = gson.fromJson(array.toString(), callBack.mType);

                        callBack.onSuccess(obj);

                    } catch (JsonParseException e) {
                        callBack.onError(e);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


    public enum RequestType {
        GET, POST
    }


}
