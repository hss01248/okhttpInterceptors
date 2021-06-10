package com.hss01248.interceptors;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 基于interceptor,实现6种请求模式.
 * NO_CACHE：不使用缓存，该模式下cacheKey、cacheTime 参数均无效
 * DEFAULT：按照HTTP协议的默认缓存规则，例如有304响应头时缓存。
 * REQUEST_FAILED_READ_CACHE：先请求网络，如果请求网络失败，则读取缓存，如果读取缓存失败，本次请求失败。
 * IF_NONE_CACHE_REQUEST：如果缓存不存在才请求网络，否则使用缓存。
 * FIRST_CACHE_THEN_REQUEST：先使用缓存，不管是否存在，仍然请求网络。
 *
 * 问题: 如何实现多次回调?
 */
public class CacheModeInterceptor extends BaseInterceptor{
    @Override
    protected Response interceptReally(Chain chain) throws IOException {
        Request request = chain.request();

        Response cache = null;
        return cache;
       //return chain.proceed(request);

    }
}
