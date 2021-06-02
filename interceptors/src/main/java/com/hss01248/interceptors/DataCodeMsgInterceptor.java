package com.hss01248.interceptors;


import java.io.IOException;

import okhttp3.Response;

/**
 * 将http的code,msg 和body里的data,code,msg形式的统一起来:
 * 剥离body里的code,msg,覆写到Response上.
 * 剥离data,覆写给responseBody
 */
public class DataCodeMsgInterceptor extends BaseInterceptor {
    @Override
    protected Response interceptReally(Chain chain) throws IOException {
        return null;
    }
}
