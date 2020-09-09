package edu.hust.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;

import java.util.Date;

/**
 * https://cloud.tencent.com/developer/article/1425597
 * jwt util
 * @author chain
 * @date 2020/9/4
 **/

@Setter
public class JWTUtil {

    private String key;


    private long ttl ;//一个小时

    /**
     * 生成JWT
     *
     */
    public String createJWT( String uid,String roles,String accountId) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("roles", roles)
                .claim("accountId",accountId) //账户
                .claim("id",uid);  //唯一主键
        if (ttl > 0) {
            builder.setExpiration( new Date( nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    /**
     * 验证token是否在有效期内
     */
    public boolean isTokenValid(Claims claims) {
            Date date = claims.getExpiration();
            return new Date().before(date);
    }

    public boolean isTokenValid(String jwt){
        return isTokenValid(parseJWT(jwt));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
