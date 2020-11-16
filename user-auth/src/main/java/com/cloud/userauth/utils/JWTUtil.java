package com.cloud.userauth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
    //秘钥
    public static final String SECRET_KEY = "123456";
    //token过期时间
    public static final long TOKEN_EXPIRE_TIME = 30 * 60 * 1000;
    //refreshToken过期时间
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 10 * 60 * 1000;
    //签发人
    private static final String ISSUER = "issuer";

    /**
     * 生成签名
     */
    public static String generateToken(String username) {
        Date now = new Date();
        //算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        String token = JWT.create()
                //签发人
                .withIssuer(ISSUER)
                //签发时间
                .withIssuedAt(now)
                //过期时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                //保存身份标识
                .withClaim("username", username)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token
     */
    public static boolean verify(String token) {
        try {
            //算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 从token获取username
     */
    public static String getUsername(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}  