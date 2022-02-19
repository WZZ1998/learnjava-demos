package com.rxcay.learnjava.demos.network;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 10/14/21 6:10 下午
 * @description
 */
public class SimpleHTTPDemo {
    public static void test() {
        List<Integer> responseLens = new ArrayList<>();
        final var THREAD_CNT = 16;
        var cl = new CountDownLatch(THREAD_CNT);
        for (int i = 0; i < THREAD_CNT; i++) {
            new Thread(() -> {
                synchronized (SimpleHTTPDemo.class) {
                    responseLens.add(executeGetWithResponseLen());
                }
                cl.countDown();
            }).start();
        }
        try {
            cl.await();
            assert responseLens.size() == THREAD_CNT;
            System.out.println(responseLens);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static int executeGetWithResponseLen() {
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        var getRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://www.gov.cn/"))
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac " +
                        "OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/81.0.4044.122 Safari/537.36")
                .build();
        try {
            var response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            return response.body().length();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
