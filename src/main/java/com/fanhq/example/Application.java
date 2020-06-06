package com.fanhq.example;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * Hello world!
 */
public class Application {


    public static void main(String[] args) throws Exception {
        SSLContext sslContext = sslContext("C:\\Users\\Lenovo\\ssl_server.jks", "Iot@2020");
//        SSLParameters sslParameters = new SSLParameters();
//        String responseBody = HttpClient.newBuilder().sslContext(sslContext).sslParameters(sslParameters).build()
//                .send(HttpRequest.newBuilder(new URI("https://127.0.0.1:9003/demo/receive"))
//                        .GET()
//                        .build(), HttpResponse.BodyHandlers.ofString()).body();
//        System.out.println(responseBody);

       // TrustManager[] trustAllCerts = {new MyX509TrustManager()};
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        URL url = new URL("https://127.0.0.1:9003/demo/receive");
        // 打开restful链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");// POST GET PUT DELETE
        // 设置访问提交模式，表单提交
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setConnectTimeout(130000);// 连接超时 单位毫秒
        conn.setReadTimeout(130000);// 读取超时 单位毫秒
        // 读取请求返回值
        byte bytes[] = new byte[1024];
        InputStream inStream = conn.getInputStream();
        inStream.read(bytes, 0, inStream.available());
        System.out.println(new String(bytes, "utf-8"));
    }


    private static SSLContext sslContext(String keystoreFile, String password)
            throws GeneralSecurityException, IOException {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        try (InputStream in = new FileInputStream(keystoreFile)) {
            keystore.load(in, password.toCharArray());
        }
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keystore, password.toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keystore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(
                keyManagerFactory.getKeyManagers(),
                trustManagerFactory.getTrustManagers(),
                new SecureRandom());

        return sslContext;
    }

}


