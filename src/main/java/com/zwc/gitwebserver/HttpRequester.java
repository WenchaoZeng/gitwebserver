package com.zwc.gitwebserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpRequester {

    static CloseableHttpClient httpClient = HttpClients
        .custom()
        .setMaxConnTotal(200)
        .setMaxConnPerRoute(20)
        .build();

    public static byte[] downloadBytes(String url) {

        HttpRequestBase httpRequest = new HttpGet(url);

        try {
            try (CloseableHttpResponse response = httpClient.execute(httpRequest)) {
                HttpEntity entity = response.getEntity();
                int code = response.getStatusLine().getStatusCode();
                if (entity != null && code == 200) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    entity.writeTo(stream);
                    return stream.toByteArray();
                }

                String msg = String.format("Failed pulling %s, http code: %d", httpRequest.getURI(), code);
                throw new IOException(msg);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}