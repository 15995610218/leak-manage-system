package com.whl.store.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static String signature = "Aa123456!";
    private static long time = 1000*60*60;
    public static String createToken(String username,String role){

        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username",username)
                .claim("role",role)
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String token){
        if (token == null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static String getUsername(String token){
        if (token == null){
            return null;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
            String username = (String) claimsJws.getBody().get("username");
            return username;
        }catch (Exception e){
            return null;
        }
    }
}
