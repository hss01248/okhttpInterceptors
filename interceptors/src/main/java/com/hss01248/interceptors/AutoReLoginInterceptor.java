package com.hss01248.interceptors;

import java.io.IOException;

import okhttp3.Response;

public class AutoReLoginInterceptor extends BaseInterceptor{
    @Override
    protected Response interceptReally(Chain chain) throws IOException {
        return null;
    }
}
