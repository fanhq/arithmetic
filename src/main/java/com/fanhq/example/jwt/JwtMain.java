package com.fanhq.example.jwt;

import com.google.common.io.Files;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.io.File;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/23
 */
public class JwtMain {

    public static void main(String[] args) throws Exception{

        File file = new File("D:\\file\\my-secret.key");
        byte[] bytes = Files.toByteArray(file);
        String data = Jwts.builder()
                .setSubject("test-user")
                .signWith(Keys.hmacShaKeyFor(bytes)).compact();
        System.out.println(data);
    }
}
