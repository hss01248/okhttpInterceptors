package com.hss01248.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Response;

/** * shouldIntercept方法优先级最高,然后才是白名单,黑名单
 * 白名单,黑名单
 * <p>
 * 优先白名单,只有在白名单里的,才拦截
 * 有白名单,但不在白名单里,就不拦截
 * <p>
 * 有黑名单,在黑名单里的,就不拦截
 * 不在黑名单里,就拦截
 * <p>
 * 都不加 == 所有请求都不拦截
 */
public abstract class BaseInterceptor implements Interceptor {

    protected Set<String> whiteListUrlPattern;
    protected Set<String> blackListUrlPattern;

    public BaseInterceptor setDebug(boolean debug) {
        isDebug = debug;
        return this;
    }

    protected boolean isDebug;
    protected boolean interceptorAll;
    public BaseInterceptor interceptorAll(boolean interceptorAll){
        this.interceptorAll = interceptorAll;
        return this;
    }

    public BaseInterceptor addWhiteList(String pattern) {
        if (whiteListUrlPattern == null) {
            whiteListUrlPattern = new HashSet<>();
        }
        whiteListUrlPattern.add(pattern);
        return this;
    }

    public BaseInterceptor addBlackList(String pattern) {
        if (blackListUrlPattern == null) {
            blackListUrlPattern = new HashSet<>();
        }
        blackListUrlPattern.add(pattern);
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(interceptorAll){
            return interceptReally(chain);
        }

        if(shouldInterceptBeforeUrlWhiteBlackList(chain)){
            return interceptReally(chain);
        }
        String url = chain.request().url().toString();

        if (whiteListUrlPattern != null && !whiteListUrlPattern.isEmpty()) {
            //优先白名单,只有在白名单里的,才拦截
            for (String pattern : whiteListUrlPattern) {
                if (url.contains(pattern)) {
                    return interceptReally(chain);
                }
            }
            //有白名单,但不在白名单里,就不拦截
            return chain.proceed(chain.request());
        }else if (blackListUrlPattern != null && !blackListUrlPattern.isEmpty()) {
            //有黑名单,在黑名单里的,就不拦截
            for (String pattern : blackListUrlPattern) {
                if (url.contains(pattern)) {
                    return chain.proceed(chain.request());
                }
            }
            //不在黑名单里,就拦截
            return interceptReally(chain);
        }
        return chain.proceed(chain.request());

    }

    protected boolean shouldInterceptBeforeUrlWhiteBlackList(Chain chain) {
        return false;
    }

    protected abstract Response interceptReally(Chain chain) throws IOException;
}
