//package com.whl.store.shiro;
//
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.whl.store.services.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtTools {
//    //过期时间
//    private static final long expire_time= 1*60*60*1000;
//    //密钥
//    private static final String secret="jwt+shiro";
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    /**
//     * 生产token
//     */
//    public static String createToken(String username){
//        try {
//            Date date = new Date(System.currentTimeMillis()+expire_time);
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            //jwt头部
//            Map<String,Object> map = new HashMap<>();
//            map.put("alg","HS256");
//            map.put("typ","JWT");
//            return JWT.create()
//                    .withHeader(map)
//                    .withClaim("username",username)
//                    .withExpiresAt(date)
//                    .withIssuedAt(new Date())
//                    .sign(algorithm);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//    /**
//     * 校验token是否正确
//     * 1、token的header和payload是否未改动
//     * 2、token是否未过期
//     */
//    public static boolean verify(String token){
//        try{
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
//            jwtVerifier.verify(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//    /**
//     * 获取token中的信息。无需解密获取
//     */
//    public static String getUsername(String token){
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return jwt.getClaim("username").asString();
//        }catch (JWTDecodeException e){
//            return null;
//        }
//    }
//    public static String getCurrentUsername(HttpServletRequest request){
//        String accessToken = request.getHeader("token");
//        return getUsername(accessToken);
//    }
//}
