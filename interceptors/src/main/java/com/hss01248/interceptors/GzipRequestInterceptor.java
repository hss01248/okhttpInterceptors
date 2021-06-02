package com.hss01248.interceptors;


import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;

import okio.GzipSink;
import okio.Okio;


public class GzipRequestInterceptor extends BaseInterceptor {



    @Override
    public Response interceptReally(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if(originalRequest.body() == null){
            return chain.proceed(originalRequest);
        }

        if (originalRequest.header("Content-Encoding") != null) {
            Log.w("gzip","originalRequest.header(\"Content-Encoding\") is "+originalRequest.header("Content-Encoding"));
            return chain.proceed(originalRequest);
        }

        Request compressedRequest = originalRequest.newBuilder()
                .header("Content-Encoding", "gzip")
                .method(originalRequest.method(), forceContentLength(gzip(originalRequest.body())))
                .build();
        return chain.proceed(compressedRequest);
    }



    /**
     * https://github.com/square/okhttp/issues/350
     */
    private RequestBody forceContentLength(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        final long size = buffer.size();
        final byte[] bytes = new byte[(int) size];
        buffer.readFully(bytes);

        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return requestBody.contentType();
            }

            @Override
            public long contentLength() {
                return size;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                //sink.write(buffer.snapshot());
                sink.write(bytes);
            }
        };
    }



    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1; // We don't know the compressed length in advance!
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
