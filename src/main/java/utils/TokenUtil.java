package utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    //设置过期时间
    private static final long EXPIRE_DATE = 15 * 24 * 60 * 60 * 1000;
    //token秘钥
    private static final String TOKEN_SECRET1 = "HasakiAtouliAkatoHasakideath";
    private static final String TOKEN_SECRET2 = "HasakiAtouliAkatoHasakiadmin";

    public static String token(int id, String name, String avatar, int auth) {
        String token;
        try {
            String secret;
            if (auth == 1)
                secret = TOKEN_SECRET1;
            else
                secret = TOKEN_SECRET2;
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id", id)
                    .withClaim("name", name)
                    .withClaim("avatar", avatar)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    public static boolean verify(String token, int auth) {
        try {
            String secret;
            if (auth == 1)
                secret = TOKEN_SECRET1;
            else
                secret = TOKEN_SECRET2;
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getTokenContext(String token, int auth) {
        String secret;
        if (auth == 1)
            secret = TOKEN_SECRET1;
        else
            secret = TOKEN_SECRET2;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        JSONObject ret = new JSONObject();
        ret.put("id", jwt.getClaim("id").asInt());
        ret.put("name", jwt.getClaim("name").asString());
        ret.put("avatar", jwt.getClaim("avatar").asString());
        return ret;
    }
}