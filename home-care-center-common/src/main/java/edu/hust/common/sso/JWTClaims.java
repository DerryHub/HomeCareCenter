package edu.hust.common.sso;

import java.util.HashMap;

/**
 * https://cloud.tencent.com/developer/article/1425597
 * @author chain
 * @date 2020/9/4
 **/
public class JWTClaims extends HashMap<String,String> {
    private static final String ID="id";
    private static final  String ACCOUNT="account";
    private static final String TYPE="type";
    private static final String FAIL_TIME="failTime";

    public JWTClaims(){
        //针对 账号，ID，type，过期时间做加密
        this.put(ID,null);
        this.put(ACCOUNT,null);
        this.put(TYPE,null);
        this.put(FAIL_TIME,null);
    }

    public JWTClaims putKV(String key,String val){
        this.put(key, val);
        return this;
    }
}
