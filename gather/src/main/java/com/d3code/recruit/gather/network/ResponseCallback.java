package com.d3code.recruit.gather.network;

/**
 * Created by Nottyjay on 2016/11/2 0002.
 */
public interface ResponseCallback {

    /**
     * HTTP响应后回调方法
     *
     * @param resultCode
     * @param result
     */
    void onResponse(int resultCode, String result);
}
