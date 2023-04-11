package com.whl.store.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwtUtils {
    public static final long EXPIRE_TIME = 5*60*1000;
    public static final long REFRESH_EXPIRE_TIME=30*60;
    public static final String TOKEN_SECRET="Aa123456!";

    /**
     * 生成token
     * @param account
     * @param currentTime
     * @return
     */
    public static String sign(String account,Long currentTime){
        String token=null;
        try{
            Date expireAt = new Date(currentTime+EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("account",account)
                    .withClaim("currentTime",currentTime)
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (IllegalArgumentException | JWTCreationException je){

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     * @throws Exception
     */
    public static Boolean verify(String token) throws Exception{
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
        DecodedJWT decodedJWT= jwtVerifier.verify(token);
        System.out.println("认证通过");
        System.out.println("account" + decodedJWT.getClaim("account").asString());
        System.out.println("过期时间: " + decodedJWT.getExpiresAt());
        return true;
    }

    /**
     * 获取token中的account
     * @param token
     * @return
     */
    public static String getAccount(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
                return decodedJWT.getClaim("account").asString();
        }catch (JWTCreationException e){
            return null;
        }

    }

    /**
     * 获取token中的当前时间
     * @param token
     * @return
     */
    public static Long getCurrentTime(String token){
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("currentTime").asLong();
        }catch (JWTCreationException e){
            return null;
        }
    }
}
