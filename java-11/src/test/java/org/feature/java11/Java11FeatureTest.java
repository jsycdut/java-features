package org.feature.java11;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class Java11FeatureTest {
    /**
     * 新的Http客户端
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testHttpClient() throws IOException, InterruptedException, ExecutionException {
        var req = HttpRequest.newBuilder()
                .uri(URI.create("http://cip.cc"))
                .header("User-Agent", "curl/8.5.0")
                .GET()
                .build();

        var client = HttpClient.newHttpClient();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("同步请求结果: " + resp.body());


        // syndAsync虽然是异步的，但是如果不管这个调用返回的CompletableFuture，直接主线程退出的话
        // 需要考虑这个future里面的代码执行是否会达到你想要的效果，比如打印输出，记录日志，数据库操作，远程操作等
        client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> System.out.println("异步请求结果：" + body))
                .get();
    }

    /**
     * Optional新增了isEmpty方法，其实和isPresent类似，具体点进去看俩代码实现，都是对value的判断
     * isPresent => value != null
     * isEmpty => value == null
     */
    @Test
    public void testOptional() {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.isPresent() + " " + empty.isEmpty());
    }

    @Test
    public void testStringEnhance() {
        String str = " hello ";
        System.out.println(str.stripLeading());
        System.out.println(str.stripTrailing());
        System.out.println(str.strip());
        System.out.println(str.repeat(3));
        System.out.println(str.isBlank());
        System.out.println(str.lines().count());

    }
}
