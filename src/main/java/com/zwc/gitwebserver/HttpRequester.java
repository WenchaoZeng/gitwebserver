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

        httpRequest.setHeader("cookie", " pgv_pvi=2588442624; __da=-218967752658056; _ga=GA1.2.2089791067.1472003478; diff_view=inline; Hm_lvt_988cde00585dc088b30fc267682deb5d=1535608239,1535608796,1535608797,1535703524; sidebar_collapsed=false; __did=-067776376265020; _gitlab_session=1bed780b6b74c398f53bcca4ff7a4b64; event_filter=all");
        httpRequest.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        httpRequest.setHeader("accept-encoding", "gzip, deflate, br");
        httpRequest.setHeader("accept-language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,zh-TW;q=0.6,lb;q=0.5,ja;q=0.4,cy;q=0.3");
        httpRequest.setHeader("cache-control", "max-age=0");
        httpRequest.setHeader("upgrade-insecure-requests", "upgrade-insecure-requests");
        httpRequest.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");

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