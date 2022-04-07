package com.fanhq.example.jwt;

import com.google.common.io.Files;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/23
 */
public class JwtMain {

    public static void main(String[] args) throws Exception{

        File file = new File("D:\\file\\my-secret.key");
        byte[] bytes = Files.toByteArray(file);
        String key = Base64.getEncoder().encodeToString(bytes);
        byte[] decode = Base64.getDecoder().decode(key);
        System.out.println(key);
        String data = Jwts.builder()
                .setSubject("test-user")
                .signWith(Keys.hmacShaKeyFor(decode)).compact();
        System.out.println(data);
    }
}
