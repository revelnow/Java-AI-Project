package http;

import java.nio.charset.StandardCharsets;

public class HttpPostRawBuilderDemo {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        String path = "/api/chat";

        // 最小 JSON body（你后面调用 LLM API 就是这种结构）
        String jsonBody = "{\"msg\":\"hi\"}";

        // Content-Length = body 的 UTF-8 字节数（不是字符数）
        int contentLength = jsonBody.getBytes(StandardCharsets.UTF_8).length;

        // HTTP 报文 = 请求行 + 请求头 + 空行 + 请求体
        String rawRequest =
                "POST " + path + " HTTP/1.1\r\n" +
                        "Host: " + host + ":" + port + "\r\n" +
                        "Content-Type: application/json\r\n" +
                        "Content-Length: " + contentLength + "\r\n" +
                        "\r\n" +
                        jsonBody;

        System.out.println("===== RAW HTTP REQUEST =====");
        System.out.println(rawRequest);

        // 可选：打印长度校验
        System.out.println("\n[check] contentLength = " + contentLength);
    }
}

